package com.bancoserfinanza.bookbank.dao;

import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.request.BookRequest;
import com.bancoserfinanza.models.response.BookResponse;

import java.util.List;

public interface BookDAO {

    Book save(BookRequest bookRequest);
    Book update(BookRequest bookRequest);

    void delete(Long bookId);

    List<BookResponse> getBooks();
}
