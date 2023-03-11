package com.bancoserfinanza.bookbank.exception;

import com.bancoserfinanza.models.enums.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BookNotFoundException() {
    }
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookNotFoundException(ResponseCodes response) {
        super(response.getMessage());
    }

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(Throwable cause) {
        super(cause);
    }

}
