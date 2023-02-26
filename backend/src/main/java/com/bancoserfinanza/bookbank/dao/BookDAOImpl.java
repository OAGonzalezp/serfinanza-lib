package com.bancoserfinanza.bookbank.dao;

import com.bancoserfinanza.bookbank.repository.IBookRepository;
import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.request.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDAOImpl implements BookDAO {

    @Autowired
    IBookRepository customerFeedbackRepository;

    @Override
    public Book save(BookRequest bookRequest) {
        Book book = new Book();
        book.setIdlib(bookRequest.getIdlib());
        book.setBookName(bookRequest.getBookName());

        return customerFeedbackRepository.save(book);
    }
}
