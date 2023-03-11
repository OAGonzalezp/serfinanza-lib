package com.bancoserfinanza.bookbank.exception;

import com.bancoserfinanza.models.enums.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerNotCreatedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomerNotCreatedException() {
    }
    public CustomerNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNotCreatedException(ResponseCodes response) {
        super(response.getMessage());
    }

    public CustomerNotCreatedException(String message) {
        super(message);
    }

    public CustomerNotCreatedException(Throwable cause) {
        super(cause);
    }

}
