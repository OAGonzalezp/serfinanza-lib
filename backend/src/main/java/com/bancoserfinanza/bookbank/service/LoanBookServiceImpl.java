package com.bancoserfinanza.bookbank.service;

import com.bancoserfinanza.bookbank.dao.LoanBookDAO;
import com.bancoserfinanza.models.bookbank.LoanBook;
import com.bancoserfinanza.models.request.LoanBookRequest;
import com.bancoserfinanza.models.response.LoanBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanBookServiceImpl implements LoanBookService {

    @Autowired
    LoanBookDAO loanBookDAO;

    @Override
    public Optional<LoanBook> save(LoanBookRequest loanBookRequest) {
        return Optional.of(loanBookDAO.save(loanBookRequest));
    }

    @Override
    public Optional<LoanBook> update(LoanBookRequest loanBookRequest) {
        return Optional.of(loanBookDAO.update(loanBookRequest));
    }

    @Override
    public void delete(Long loanBookId) {
        loanBookDAO.delete(loanBookId);
    }

    @Override
    public List<LoanBookResponse> getLoanBooks() {
        return loanBookDAO.getLoanBooks();
    }
}
