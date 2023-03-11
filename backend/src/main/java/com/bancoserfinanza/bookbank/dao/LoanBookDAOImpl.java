package com.bancoserfinanza.bookbank.dao;

import com.bancoserfinanza.bookbank.exception.BookNamePalindromeException;
import com.bancoserfinanza.bookbank.exception.BookNotFoundException;
import com.bancoserfinanza.bookbank.exception.BookTakenFoundException;
import com.bancoserfinanza.bookbank.repository.IBookRepository;
import com.bancoserfinanza.bookbank.repository.ILoanBookRepository;
import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.bookbank.LoanBook;
import com.bancoserfinanza.models.enums.BookStatus;
import com.bancoserfinanza.models.request.LoanBookRequest;
import com.bancoserfinanza.models.response.LoanBookResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanBookDAOImpl implements LoanBookDAO {

    @Autowired
    ILoanBookRepository iLoanBookRepository;

    @Autowired
    IBookRepository iBookRepository;

    @Override
        public LoanBook save(LoanBookRequest loanBookRequest) {
        LoanBook loanBook = new LoanBook();

        Book book = iBookRepository.getById(loanBookRequest.getBook().getId());
        if (book == null) {
            throw new BookNotFoundException();
        }

        if (BookStatus.TAKEN.equals(book.getStatus())) {
            throw new BookTakenFoundException();
        }
        String idLib = book.getIdlib();

        if (new StringBuilder(idLib).reverse().toString().equals(book.getIdlib())) {
            throw new BookNamePalindromeException();
        }

        LocalDate loanDate = LocalDate.now();
        LocalDate deliveryDate = LocalDate.now();

        BeanUtils.copyProperties(loanBookRequest, loanBook);

        if (idLib.length() > 40) {
            deliveryDate = getDeliveryDate(5);
            loanBook.setDeliveryDate(Date.from(deliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        loanBook.setLoanDate(new Date());
        loanBook.setStatus(BookStatus.ACTIVE);
        loanBook.getBook().setStatus(BookStatus.TAKEN);
        loanBook.getBook().setLastTakenDate(new Date());

        iBookRepository.save(loanBook.getBook());

        return iLoanBookRepository.save(loanBook);
    }

    @Override
    public LoanBook update(LoanBookRequest loanBookRequest) {
        LoanBook loanBook = iLoanBookRepository.getById(loanBookRequest.getId());
        if (loanBook == null) {
            throw new IllegalArgumentException("LoanBook not found");
        }

        BeanUtils.copyProperties(loanBookRequest, loanBook, "id");
        loanBookRequest.setDeliveryDate(new Date());
        loanBook.setStatus(BookStatus.DELIVERED);
        loanBook.getBook().setStatus(BookStatus.ACTIVE);

        iBookRepository.save(loanBook.getBook());

        return iLoanBookRepository.save(loanBook);
    }

    @Override
    public void delete(Long loanBookId) {
        LoanBook loanBook = iLoanBookRepository.getById(loanBookId);
        if (loanBook == null) {
            throw new IllegalArgumentException("LoanBook not found");
        }

        iLoanBookRepository.delete(loanBook);
    }

    @Override
    public List<LoanBookResponse> getLoanBooks() {
        List<LoanBook> loanBooks = iLoanBookRepository.findAll();
        return loanBooks.stream().map(x -> LoanBookResponse.build(x)).collect(Collectors.toList());
    }


    public LocalDate getDeliveryDate(int days) {
        LocalDate date = LocalDate.now();
        int countDays = 0;
        while (countDays < days) {
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() != 7) {
                countDays++;
            }
        }

        if (date.getDayOfWeek().getValue() == 7) {
            date = date.plusDays(1);
        }

        return date;
    }

}
