package com.bancoserfinanza.bookbank.service;

import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.request.BookRequest;
import com.bancoserfinanza.models.response.BookResponse;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> save(BookRequest bookRequest);
    Optional<Book> update(BookRequest bookRequest);

    List<BookResponse> getBooks();
}
