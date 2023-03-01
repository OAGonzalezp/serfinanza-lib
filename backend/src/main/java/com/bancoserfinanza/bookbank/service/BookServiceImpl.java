package com.bancoserfinanza.bookbank.service;

import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.bookbank.dao.BookDAO;
import com.bancoserfinanza.models.request.BookRequest;
import com.bancoserfinanza.models.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDAO bookDAO;

    @Override
    public Optional<Book> save(BookRequest bookRequest) {
        return Optional.of(bookDAO.save(bookRequest));
    }

    @Override
    public Optional<Book> update(BookRequest bookRequest) {
        return Optional.of(bookDAO.update(bookRequest));
    }

    @Override
    public List<BookResponse> getBooks() {
        return bookDAO.getBooks();
    }
}
