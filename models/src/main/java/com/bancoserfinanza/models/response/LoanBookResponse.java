package com.bancoserfinanza.models.response;

import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.bookbank.Customer;
import com.bancoserfinanza.models.bookbank.LoanBook;
import com.bancoserfinanza.models.enums.BookStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class LoanBookResponse implements Serializable {
    private Long id;

    private BookResponse book;

    private CustomerResponse customer;

    private Date loanDate;

    private Date deliveryDate;

    private BookStatus status;


    public static LoanBookResponse build(LoanBook loanBook) {
        LoanBookResponse loanBookResponse = new LoanBookResponse();
        loanBookResponse.setId(loanBook.getId());
        loanBookResponse.setCustomer(CustomerResponse.build(loanBook.getCustomer()));
        loanBookResponse.setBook(BookResponse.build(loanBook.getBook()));
        loanBookResponse.setLoanDate(loanBook.getLoanDate());
        loanBookResponse.setStatus(loanBook.getStatus());
        loanBookResponse.setDeliveryDate(loanBook.getDeliveryDate());

        return loanBookResponse;
    }

}
