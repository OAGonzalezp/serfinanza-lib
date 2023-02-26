package com.bancoserfinanza.bookbank.exception;

import com.bancoserfinanza.models.enums.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookNotCreatedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BookNotCreatedException() {
    }
    public BookNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookNotCreatedException(ResponseCodes response) {
        super(response.getMessage());
    }

    public BookNotCreatedException(String message) {
        super(message);
    }

    public BookNotCreatedException(Throwable cause) {
        super(cause);
    }

}
