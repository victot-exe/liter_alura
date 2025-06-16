package com.victot_exe.liter_alura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record AuthorDTO(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") Integer bithYear,
        @JsonAlias("death_year") Integer deathYear
) {
}
