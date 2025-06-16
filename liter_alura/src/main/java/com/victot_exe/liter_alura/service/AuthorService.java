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

    public List<AuthorDTO> getAutores(){
        return authorRepository.findAll().stream().map(
                a -> new AuthorDTO(
                        a.getName(),
                        a.getBirthYear(),
                        a.getDeathYear()
                ))
        .collect(Collectors.toList());
    }

    public List<AuthorDTO> getAuthorLivingInYear(int year){

        return authorRepository.findAuthorsAliveInYear(year)
                .stream()
                .map(
                        a -> new AuthorDTO(
                                a.getName(),
                                a.getBirthYear(),
                                a.getDeathYear()
                        ))
                .collect(Collectors.toList());
    }
}
