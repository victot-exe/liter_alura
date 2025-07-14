package com.victot_exe.liter_alura.service;

import com.victot_exe.liter_alura.dto.AuthorDTO;
import com.victot_exe.liter_alura.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    //Opcao 3
    public List<AuthorDTO> getAutores(){
        return authorRepository.findAll()
                .stream().map(AuthorDTO::from)
                .collect(Collectors.toList());
    }

    //Opcao 4
    public List<AuthorDTO> getAuthorLivingInYear(int year){

        return authorRepository.findAuthorsAliveInYear(year)
                .stream()
                .map(AuthorDTO::from)
                .collect(Collectors.toList());
    }
}
