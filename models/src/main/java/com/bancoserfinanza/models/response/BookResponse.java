package com.bancoserfinanza.models.response;

import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.request.BookRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookResponse extends BookRequest {
    private Long id;
    private String idlib;
    private String bookName;

    public static BookResponse build(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setIdlib(book.getIdlib());
        bookResponse.setBookName(book.getBookName());

        return bookResponse;
    }
}
