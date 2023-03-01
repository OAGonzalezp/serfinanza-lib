package com.bancoserfinanza.models.response;

import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.enums.BookStatus;
import com.bancoserfinanza.models.request.BookRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookResponse {
    private Long id;
    private String idlib;
    private String bookName;
    private BookStatus status;

    public BookResponse() {
    }

    public BookResponse(Long id, String idlib, String bookName) {
        this.id = id;
        this.idlib = idlib;
        this.bookName = bookName;
    }

    public static BookResponse build(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setIdlib(book.getIdlib());
        bookResponse.setBookName(book.getBookName());
        bookResponse.setStatus(book.getStatus());

        return bookResponse;
    }
}
