package com.bancoserfinanza.bookbank.advice;

import com.bancoserfinanza.bookbank.exception.*;
import com.bancoserfinanza.models.enums.ResponseCodes;
import com.bancoserfinanza.models.response.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionAdvice {
	
	@Autowired
    HttpServletRequest httpServletRequest;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> processUnmergeException(final MethodArgumentNotValidException ex) {

        List<String> messages = ex.getBindingResult().getAllErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        DataResponse response = new DataResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(messages.stream().collect(Collectors.joining(",")));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotCreatedException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> bookCreateNOK(final BookNotCreatedException ex) {
        return new ResponseEntity(ResponseCodes.NOK_BOOK_NOT_CREATED, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotCreatedException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> customerCreateNOK(final CustomerNotCreatedException ex) {
        return new ResponseEntity(ResponseCodes.NOK_CUSTOMER_NOT_CREATED, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookTakenFoundException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> bookTakenNOK(final BookTakenFoundException ex) {
        return new ResponseEntity(ResponseCodes.NOK_BOOK_TAKEN, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> bookNotFoundNOK(final BookNotFoundException ex) {
        return new ResponseEntity(ResponseCodes.NOK_BOOK_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoanBookNotCreatedException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> loanBookCreateNOK(final LoanBookNotCreatedException ex) {
        return new ResponseEntity(ResponseCodes.NOK_LOAN_BOOK_NOT_CREATED, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNamePalindromeException.class)
    @ResponseBody
    public ResponseEntity<DataResponse> takePalindromeBookAttempt(final BookNamePalindromeException ex) {
        return new ResponseEntity(ResponseCodes.NOK_PALINDROME_BOOK_NAME, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<DataResponse> processGenericException(final Exception ex) {

        DataResponse response = new DataResponse();
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(ex.getMessage());

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
