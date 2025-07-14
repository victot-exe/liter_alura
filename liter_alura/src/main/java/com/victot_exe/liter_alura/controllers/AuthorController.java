package com.victot_exe.liter_alura.controllers;

import com.victot_exe.liter_alura.dto.AuthorDTO;
import com.victot_exe.liter_alura.service.AuthorService;

import java.util.List;

public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    public List<AuthorDTO> getAuthors() {
        return authorService.getAutores();
    }

    public List<AuthorDTO> getAuthorsWithLivingInYear(int year){
        return authorService.getAuthorLivingInYear(year);
    }
}
