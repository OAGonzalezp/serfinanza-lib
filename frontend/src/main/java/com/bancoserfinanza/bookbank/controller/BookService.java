package com.bancoserfinanza.bookbank.controller;

import com.bancoserfinanza.bookbank.util.Utilities;
import com.bancoserfinanza.models.request.BookRequest;
import com.bancoserfinanza.models.response.BookResponse;
import com.bancoserfinanza.models.response.BookListResponseData;
import com.bancoserfinanza.models.response.BookResponseData;
import okhttp3.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class BookService {

    public static final String API_URL = "http://localhost:8080/v0/book";

    private final OkHttpClient httpClient = new OkHttpClient();

    @PostConstruct
    public void init() {
    }


    public List<BookResponse> loadBooks() {
        try {
            return sendGetBooks();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<BookResponse> sendGetBooks() throws Exception {
        List<BookResponse> books = new ArrayList<>();
        Request request = new Request.Builder()
                .url(API_URL)
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

        return books;
    }

    public BookResponse updateBook(BookRequest bookRequest) {
        try {
            return sendUpdateBook(bookRequest);
        } catch (Exception e) {
            return null;
        }
    }

    private BookResponse sendUpdateBook(BookRequest bookRequest) throws Exception {
        // form parameters
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), Utilities.marshalRequest(bookRequest));

        Request request = new Request.Builder()
                .url(API_URL)
                .put(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            BookResponseData responseData = Utilities.unmarshalResponse(response.body(), BookResponseData.class);
            if (responseData.getCode() == 1) {
                return responseData.getData();
            } else {
                return null;
            }
        }
    }

    public BookResponse saveBook(BookRequest bookRequest) {
        try {
            return sendSaveBook(bookRequest);
        } catch (Exception e) {
            return null;
        }
    }

    private BookResponse sendSaveBook(BookRequest bookRequest) throws Exception {
        // form parameters
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), Utilities.marshalRequest(bookRequest));

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            BookResponseData responseData = Utilities.unmarshalResponse(response.body(), BookResponseData.class);
            if (responseData.getCode() == 1) {
                return responseData.getData();
            } else {
                return null;
            }
        }
    }
}