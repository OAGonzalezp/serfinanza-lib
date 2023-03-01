package com.bancoserfinanza.bookbank.dao;

import com.bancoserfinanza.bookbank.repository.IBookRepository;
import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.enums.BookStatus;
import com.bancoserfinanza.models.request.BookRequest;
import com.bancoserfinanza.models.response.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookDAOImpl implements BookDAO {

    @Autowired
    IBookRepository iBookRepository;

    @Override
    public Book save(BookRequest bookRequest) {
        Book book = new Book();
        book.setIdlib(bookRequest.getIdlib());
        book.setBookName(bookRequest.getBookName());
        book.setStatus(BookStatus.ACTIVE);

        return iBookRepository.save(book);
    }

    @Override
    public Book update(BookRequest bookRequest) {
        Book book = iBookRepository.getById(bookRequest.getId());
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        book.setIdlib(bookRequest.getIdlib());
        book.setBookName(bookRequest.getBookName());

        if (bookRequest.getStatus() != null) {
            book.setStatus(bookRequest.getStatus());
        }

        return iBookRepository.save(book);
    }

    @Override
    public List<BookResponse> getBooks() {
        List<Book> books = iBookRepository.findAll();
        return books.stream().map(x -> BookResponse.build(x)).collect(Collectors.toList());
    }
}
