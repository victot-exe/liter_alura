package com.victot_exe.liter_alura.model;

import com.victot_exe.liter_alura.dto.BookDTOEntry;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idOnGuttendex;
    private String title;
    @ManyToOne()    @JoinColumn(name = "author_id")
    private Author author;
    @ElementCollection
    private Set<String> languages;
    private Integer downloadCount;

    public Book(){
        this.languages = new HashSet<>();
    }

    public Book(BookDTOEntry entry){
        this.idOnGuttendex = entry.id();
        this.title = entry.title();
        this.author = entry.authors().stream()
                .findFirst()
                .map(Author::new)
                .orElse(null);
        this.languages = new HashSet<>(entry.languages());
        this.downloadCount = entry.downloadCount();

    }

    public Integer getId() {
        return id;
    }

    public Integer getIdOnGuttendex() {
        return idOnGuttendex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", idOnGuttendex=" + idOnGuttendex +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", languages=" + languages +
                ", downloadCount=" + downloadCount +
                '}';
    }
}
