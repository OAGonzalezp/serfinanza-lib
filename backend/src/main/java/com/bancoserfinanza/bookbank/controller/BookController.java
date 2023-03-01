package com.bancoserfinanza.bookbank.controller;

import com.bancoserfinanza.bookbank.exception.BookNotCreatedException;
import com.bancoserfinanza.bookbank.service.BookService;
import com.bancoserfinanza.models.enums.ResponseCodes;
import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.request.BookRequest;
import com.bancoserfinanza.models.response.BookResponse;
import com.bancoserfinanza.models.response.DataResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v0")
public class BookController {

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse<BookResponse>> createBook(@Valid @RequestBody final BookRequest bookRequest) {
        DataResponse response = new DataResponse();
        logger.debug("Creating new book");
        logger.debug("Request received {}", bookRequest);

        Optional<Book> book = bookService.save(bookRequest);

        if (!book.isPresent()) {
            throw new BookNotCreatedException();
        }

        response.setCode(ResponseCodes.OK.getCode());
        response.setData(BookResponse.build(book.get()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse<BookResponse>> updateBook(@Valid @RequestBody final BookRequest bookRequest) {
        DataResponse response = new DataResponse();
        logger.debug("Updating new book");
        logger.debug("Request received {}", bookRequest);

        Optional<Book> book = bookService.update(bookRequest);

        if (!book.isPresent()) {
            throw new BookNotCreatedException();
        }

        response.setCode(ResponseCodes.OK.getCode());
        response.setData(BookResponse.build(book.get()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse<BookResponse>> getBooks() {
        DataResponse response = new DataResponse();
        logger.debug("Getting books");

        List<BookResponse> bookResponseList = bookService.getBooks();

        response.setCode(ResponseCodes.OK.getCode());
        response.setData(bookResponseList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
