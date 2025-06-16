package com.victot_exe.liter_alura.repository;

import com.victot_exe.liter_alura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    boolean existsByTitle(String title);

    @Query("SELECT b FROM Book b JOIN FETCH b.languages WHERE b.title = :title")
    Optional<Book> findBookWithLanguagesByTitle(@Param("title") String title);

    @Query("SELECT b FROM Book b JOIN FETCH b.author LEFT JOIN FETCH b.languages")
    List<Book> findAllWithAuthorAndLanguages();

    boolean existsByIdOnGuttendex(Integer idOnGuttendex);
}
