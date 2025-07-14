package com.victot_exe.liter_alura.ui;

import com.victot_exe.liter_alura.dto.AuthorDTO;
import com.victot_exe.liter_alura.dto.BookDTOExit;
import com.victot_exe.liter_alura.exception.LivroNaoEncontradoException;
import com.victot_exe.liter_alura.service.AuthorService;
import com.victot_exe.liter_alura.service.BookService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner sc;

    private BookService bookService;
    private AuthorService authorService;

    public Menu(BookService bookService, AuthorService authorService){
        sc = new Scanner(System.in);
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public void exibirMenu(){
        //TODO metodo q exibe o menu
        String mensagemInicial =
                """
                Seja bem vindo!
                Selecione o que quer fazer:
                1- Ver livros salvos no Database
                2- Obter livro por título
                3- Listar autores
                4- Listar autores vivos no ano de:
                """;
        int entrada = 0;

        while(entrada != -1){
            System.out.println(mensagemInicial);
            entrada = pegaEntradaInt();
            loop(entrada);
        }
    }

    private int pegaEntradaInt(){

        int saida = 0;
        boolean controle = true;

        while (controle){
            try {
                saida =  Integer.parseInt(pegaEntradaStr());
                controle = false;
            }catch (NumberFormatException ex){
                System.out.println("Insira um número válido");
            }
        }

        return saida;
    }

    private String pegaEntradaStr(){
        return sc.nextLine();
    }

    private void loop(int opcao){
        switch (opcao){
            case 1:
                verLivrosSalvos();
                break;
            case 2:
                obterLivroPortitulo();
                break;
            case 3:
                listarAutores();
                break;
            case 4:
                listarAutoresVivosNoAno();
                break;
            default:
                System.out.println("Insira uma opção válida ou pressione -1 para sair");
        }
    }

    private void verLivrosSalvos(){
        List<BookDTOExit> books = bookService.getBooks();
        if(books.isEmpty())
            System.out.println("Você ainda não salvou nenhum livro.");

        books.forEach(System.out::println);
    }

    private void obterLivroPortitulo(){
        System.out.println("Insira o título do livro desejado");

        String titulo = pegaEntradaStr();

        try {
            bookService.getBookByTitle(titulo);
        }catch (LivroNaoEncontradoException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void listarAutores(){
        List<AuthorDTO> authors = authorService.getAutores();
        if(authors.isEmpty())
            System.out.println("Você ainda não salvou nenhum autor.");

        authors.forEach(System.out::println);
    }

    private void listarAutoresVivosNoAno(){
        System.out.println("Insira o ano desejado:");
        int ano = pegaEntradaInt();


        if(ano > LocalDate.now().getYear()){
            System.out.println("Esse ano ainda não chegou amiguinho");
        }

        List<AuthorDTO> authors = authorService.getAuthorLivingInYear(ano);
        if(authors.isEmpty()){
            System.out.println("Ixi, nenhum autor que você pesquisou anteriormente estava vivo nesse ano");
        }

        authors.forEach(System.out::println);
    }
}
