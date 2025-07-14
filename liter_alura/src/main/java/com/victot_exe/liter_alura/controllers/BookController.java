package com.victot_exe.liter_alura.controllers;

import com.victot_exe.liter_alura.dto.BookDTOExit;
import com.victot_exe.liter_alura.service.BookService;


public class BookController {

    BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    public BookDTOExit getBookByTitle(String title){
        return bookService.getBookByTitle(title);//TODO quando for usar no menu lembrar de usar o try cath para tratar a excessão corretamente e dizer que não achou o livro
    }
}
