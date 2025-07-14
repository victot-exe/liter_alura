package com.victot_exe.liter_alura.dto;

import com.victot_exe.liter_alura.model.Book;

public record BookDTOExit(
        Integer id,
        String title,
        String authors,
        String languages,
        Integer downloadCount

) {
    public static BookDTOExit from(Book book){
        return new BookDTOExit(book.getId(),
                book.getTitle(),
                book.getAuthor().toString(),
                book.getLanguages().toString(),
                book.getDownloadCount());
    }
}
