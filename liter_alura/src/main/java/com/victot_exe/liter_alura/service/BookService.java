package com.victot_exe.liter_alura.service;

import com.victot_exe.liter_alura.dto.BookDTOEntry;
import com.victot_exe.liter_alura.dto.BookDTOExit;
import com.victot_exe.liter_alura.exception.LivroNaoEncontradoException;
import com.victot_exe.liter_alura.model.Author;
import com.victot_exe.liter_alura.model.Book;
import com.victot_exe.liter_alura.repository.AuthorRepository;
import com.victot_exe.liter_alura.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class BookService {

    private ConvertDataImpl conversor;
    private ConsumeAPI consumer;
    private final String URI = "https://gutendex.com/books/";
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BookService(ConvertDataImpl conversor, ConsumeAPI consumer, BookRepository bookRepository, AuthorRepository authorRepository){
        this.conversor = conversor;
        this.consumer = consumer;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    //Opcao 1
    public List<BookDTOExit> getBooks(){
        return bookRepository.findAllWithAuthorAndLanguages()
                .stream().map(
                        b -> new BookDTOExit(
                                b.getIdOnGuttendex(),
                                b.getTitle(),
                                b.getAuthor().getName(),
                                b.getLanguages().toString(),
                                b.getDownloadCount())
                ).collect(Collectors.toList());
    }

    //Opção 2
    public BookDTOExit getBookByTitle(String title){

        title = title.toLowerCase(Locale.ROOT);

        if(bookRepository.existsByTitle(title)){
            return BookDTOExit.from(bookRepository.getByTitle(title));
        }

        String json = consumer.getDataFromURI(
                URI + "?search=" + URLEncoder.encode(title, StandardCharsets.UTF_8).replace("+", "%20"));

        BookDTOEntry result = conversor.getData(json, BookDTOEntry.class);
        //verifica se o livro foi encontrado
        if(result == null){
            throw new LivroNaoEncontradoException("O livro: " + title + " não foi encontrado");
        }

        //verifica a correspondência exata do titulo e salva
        if(!result.title().equalsIgnoreCase(title)){
            throw new LivroNaoEncontradoException("O livro: " + title + " não foi encontrado");
        }

        return this.save(new Book(result));
    }

    private BookDTOExit save(Book book){
        //verifica se o livro existe no repositorio local
        if(bookRepository.existsByIdOnGuttendex(book.getIdOnGuttendex())){
            return BookDTOExit.from(book);
        }

        return BookDTOExit.from(bookRepository.save(book));
    }
}
