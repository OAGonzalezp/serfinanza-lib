package com.bancoserfinanza.bookbank.exception;

import com.bancoserfinanza.models.enums.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanBookNotCreatedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LoanBookNotCreatedException() {
    }
    public LoanBookNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoanBookNotCreatedException(ResponseCodes response) {
        super(response.getMessage());
    }

    public LoanBookNotCreatedException(String message) {
        super(message);
    }

    public LoanBookNotCreatedException(Throwable cause) {
        super(cause);
    }

}
