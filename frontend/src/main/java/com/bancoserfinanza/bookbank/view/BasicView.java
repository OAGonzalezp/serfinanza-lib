package com.bancoserfinanza.bookbank.view;

import com.bancoserfinanza.bookbank.controller.BookService;
import com.bancoserfinanza.bookbank.controller.CustomerService;
import com.bancoserfinanza.bookbank.controller.LoanBookService;
import com.bancoserfinanza.bookbank.util.Utilities;
import com.bancoserfinanza.models.response.BookResponse;
import com.bancoserfinanza.models.response.BookListResponseData;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name ="dtBasicView")
@ViewScoped
public class BasicView implements Serializable {

    @Getter
    private String option;
    @Getter
    private List<BookResponse> books;
    private final OkHttpClient httpClient = new OkHttpClient();
    private LoanBookService service = new LoanBookService();
    private CustomerService customerService = new CustomerService();
    private BookService bookService = new BookService();

    @PostConstruct
    public void postConstruct() {
    }

    public void changeOption(String option) {
        this.option = option;
    }



    public void loadData() {
        switch (option) {
            case "prestamos": service.loadLoanBooks(); break;
            case "clientes": customerService.loadCustomers(); break;
            case "libros": bookService.loadBooks(); break;
        }
    }
}
