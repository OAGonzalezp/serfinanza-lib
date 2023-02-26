package com.bancoserfinanza.bookbank.service;

import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.request.BookRequest;

import java.util.Optional;

public interface BookService {

    Optional<Book> save(BookRequest bookRequest);
}
