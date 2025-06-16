package com.victot_exe.liter_alura.service;

import com.victot_exe.liter_alura.dto.BookDTOEntry;
import com.victot_exe.liter_alura.dto.BookDTOExit;
import com.victot_exe.liter_alura.model.Book;
import com.victot_exe.liter_alura.repository.AuthorRepository;
import com.victot_exe.liter_alura.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
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

    public BookDTOExit getBooksByTitle(String title){//TODO colocar a correspondência exata do titulo ignorando maiuscula e minuscula é claro, caso não seja colocar livro não encontrado

        String json = consumer.getDataFromURI(
                URI + "?search=" + URLEncoder.encode(title, StandardCharsets.UTF_8).replace("+", "%20"));

        Book result = conversor.getDataToList(json, BookDTOEntry.class)
                .stream()
                .map(Book::new)
                .findFirst().orElseThrow();

        if(bookRepository.existsByTitle(result.getTitle()) || bookRepository.existsByIdOnGuttendex(result.getIdOnGuttendex())){
            Book bookDB = bookRepository.findBookWithLanguagesByTitle(result.getTitle()).get();
            return new BookDTOExit(
                    bookDB.getIdOnGuttendex(),
                    bookDB.getTitle(),
                    bookDB.getAuthor().getName(),
                    bookDB.getLanguages().toString(),
                    bookDB.getDownloadCount());
        }

        return this.save(result);
    }

    private BookDTOExit save(Book book){
        if(authorRepository.existsByName(book.getAuthor().getName())){
            bookRepository.save(book);
            return new BookDTOExit(
                    book.getIdOnGuttendex(),
                    book.getTitle(),
                    book.getAuthor().getName(),
                    book.getLanguages().toString(),
                    book.getDownloadCount());
        }
        authorRepository.save(book.getAuthor());
        bookRepository.save(book);

        return new BookDTOExit(
                book.getIdOnGuttendex(),
                book.getTitle(),
                book.getAuthor().getName(),
                book.getLanguages().toString(),
                book.getDownloadCount());
    }
}
