package com.bancoserfinanza.bookbank.view;

import com.bancoserfinanza.bookbank.controller.BookService;
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

    @Inject
    private BookService service;

    @PostConstruct
    public void postConstruct() {
    }

    public void changeOption(String option) {
        this.option = option;
        switch (option) {
            case "prestamos": loadBooks(); break;
        }
    }

    public void loadBooks() {
        try {
            sendGetBooks();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void sendGetBooks() throws Exception {

        Request request = new Request.Builder()
                .url("http://localhost:8080/v0/book")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            BookListResponseData responseData = Utilities.unmarshalResponse(response.body(), BookListResponseData.class);
            if (responseData.getCode() == 1) {
                books = responseData.getData();
            } else {
                books = new ArrayList<>();
            }
        }

    }
    public void setService(BookService service) {
        this.service = service;
    }
}
