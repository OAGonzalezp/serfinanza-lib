package com.bancoserfinanza.bookbank.controller;

import com.bancoserfinanza.bookbank.exception.LoanBookNotCreatedException;
import com.bancoserfinanza.bookbank.service.LoanBookService;
import com.bancoserfinanza.models.bookbank.LoanBook;
import com.bancoserfinanza.models.enums.ResponseCodes;
import com.bancoserfinanza.models.request.LoanBookRequest;
import com.bancoserfinanza.models.response.LoanBookResponse;
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

@CrossOrigin
@RestController
@RequestMapping(value = "/v0")
public class LoanBookController {

    Logger logger = LoggerFactory.getLogger(LoanBookController.class);

    @Autowired
    private LoanBookService loanBookService;

    @PostMapping(value = "/loanBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse<LoanBookResponse>> createLoanBook(@Valid @RequestBody final LoanBookRequest loanBookRequest) {
        DataResponse response = new DataResponse();
        logger.info("Creating new loanBook");
        logger.info("Request received {}", loanBookRequest);

        Optional<LoanBook> loanBook = loanBookService.save(loanBookRequest);

        if (!loanBook.isPresent()) {
            throw new LoanBookNotCreatedException();
        }

        response.setCode(ResponseCodes.OK.getCode());
        response.setData(LoanBookResponse.build(loanBook.get()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/loanBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse<LoanBookResponse>> updateLoanBook(@Valid @RequestBody final LoanBookRequest loanBookRequest) {
        DataResponse response = new DataResponse();
        logger.debug("Updating new loanBook");
        logger.debug("Request received {}", loanBookRequest);

        Optional<LoanBook> loanBook = loanBookService.update(loanBookRequest);

        if (!loanBook.isPresent()) {
            throw new LoanBookNotCreatedException();
        }

        response.setCode(ResponseCodes.OK.getCode());
        response.setData(LoanBookResponse.build(loanBook.get()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/loanBook/{loanBookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse<Long>> deleteLoanBook(@PathVariable Long loanBookId) {
        DataResponse response = new DataResponse();
        logger.debug("Deleting loanBook {}", loanBookId);

        loanBookService.delete(loanBookId);

        response.setCode(ResponseCodes.OK.getCode());
        response.setData(loanBookId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/loanBook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse<LoanBookResponse>> getLoanBooks() {
        DataResponse response = new DataResponse();
        logger.debug("Getting loanBooks");

        List<LoanBookResponse> loanBookResponseList = loanBookService.getLoanBooks();

        response.setCode(ResponseCodes.OK.getCode());
        response.setData(loanBookResponseList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
