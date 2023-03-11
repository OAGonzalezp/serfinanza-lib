package com.bancoserfinanza.bookbank.service;

import com.bancoserfinanza.models.bookbank.LoanBook;
import com.bancoserfinanza.models.request.LoanBookRequest;
import com.bancoserfinanza.models.response.LoanBookResponse;

import java.util.List;
import java.util.Optional;

public interface LoanBookService {

    Optional<LoanBook> save(LoanBookRequest bookRequest);
    Optional<LoanBook> update(LoanBookRequest bookRequest);
    void delete(Long loanBookId);

    List<LoanBookResponse> getLoanBooks();
}
