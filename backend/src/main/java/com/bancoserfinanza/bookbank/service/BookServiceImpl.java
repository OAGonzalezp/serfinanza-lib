package com.bancoserfinanza.bookbank.service;

import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.bookbank.dao.BookDAO;
import com.bancoserfinanza.models.request.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDAO bookDAO;

    @Override
    public Optional<Book> save(BookRequest feedbackRequest) {
        return Optional.ofNullable(bookDAO.save(feedbackRequest));
    }
}
