package com.bancoserfinanza.bookbank.exception;

import com.bancoserfinanza.models.enums.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookTakenFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BookTakenFoundException() {
    }
    public BookTakenFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookTakenFoundException(ResponseCodes response) {
        super(response.getMessage());
    }

    public BookTakenFoundException(String message) {
        super(message);
    }

    public BookTakenFoundException(Throwable cause) {
        super(cause);
    }

}
