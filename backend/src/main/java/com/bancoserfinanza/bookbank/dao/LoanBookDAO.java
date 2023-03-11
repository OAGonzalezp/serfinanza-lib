package com.bancoserfinanza.bookbank.dao;

import com.bancoserfinanza.models.bookbank.LoanBook;
import com.bancoserfinanza.models.request.BookRequest;
import com.bancoserfinanza.models.request.LoanBookRequest;
import com.bancoserfinanza.models.response.LoanBookResponse;

import java.util.List;

public interface LoanBookDAO {

    LoanBook save(LoanBookRequest bookRequest);
    LoanBook update(LoanBookRequest bookRequest);

    void delete(Long loanBookId);

    List<LoanBookResponse> getLoanBooks();
}
