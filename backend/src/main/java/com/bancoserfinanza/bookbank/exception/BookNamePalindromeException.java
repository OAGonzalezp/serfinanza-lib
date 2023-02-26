package com.bancoserfinanza.bookbank.exception;

import com.bancoserfinanza.models.enums.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookNamePalindromeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BookNamePalindromeException() {
    }
    public BookNamePalindromeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookNamePalindromeException(ResponseCodes response) {
        super(response.getMessage());
    }

    public BookNamePalindromeException(String message) {
        super(message);
    }

    public BookNamePalindromeException(Throwable cause) {
        super(cause);
    }

}
