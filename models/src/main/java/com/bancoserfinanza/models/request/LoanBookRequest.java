package com.bancoserfinanza.models.request;

import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.bookbank.Customer;
import com.bancoserfinanza.models.response.BookResponse;
import com.bancoserfinanza.models.response.LoanBookResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class LoanBookRequest implements Serializable {
    private Long id;

    private Book book;

    private Customer customer;

    private Date loanDate;

    private Date deliveryDate;

    public static LoanBookRequest buildRequest(LoanBookResponse loanBookResponse) {
        LoanBookRequest bookRequest = new LoanBookRequest();
        bookRequest.setId(loanBookResponse.getId());

        return bookRequest;
    }

}
