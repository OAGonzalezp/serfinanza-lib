package com.bancoserfinanza.bookbank.controller;

import com.bancoserfinanza.bookbank.util.Utilities;
import com.bancoserfinanza.models.request.LoanBookRequest;
import com.bancoserfinanza.models.response.LoanBookListResponseData;
import com.bancoserfinanza.models.response.LoanBookResponse;
import com.bancoserfinanza.models.response.LoanBookResponseData;
import okhttp3.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class LoanBookService {

    public static final String API_URL = "http://localhost:8080/v0/loanBook";

    private final OkHttpClient httpClient = new OkHttpClient();

    @PostConstruct
    public void init() {
    }


    public List<LoanBookResponse> loadLoanBooks() {
        try {
            return sendGetLoanBooks();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<LoanBookResponse> sendGetLoanBooks() throws Exception {
        List<LoanBookResponse> loanBooks = new ArrayList<>();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            LoanBookListResponseData responseData = Utilities.unmarshalResponse(response.body(), LoanBookListResponseData.class);
            if (responseData.getCode() == 1) {
                loanBooks = responseData.getData();
            } else {
                loanBooks = new ArrayList<>();
            }
        }

        return loanBooks;
    }

    public LoanBookResponseData updateLoanBook(LoanBookRequest loanBookRequest) {
        try {
            return sendUpdateLoanBook(loanBookRequest);
        } catch (Exception e) {
            return null;
        }
    }

    private LoanBookResponseData sendUpdateLoanBook(LoanBookRequest loanBookRequest) throws Exception {
        // form parameters
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), Utilities.marshalRequest(loanBookRequest));

        Request request = new Request.Builder()
                .url(API_URL)
                .put(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            // Get response body
            return Utilities.unmarshalResponse(response.body(), LoanBookResponseData.class);
        }
    }

    public LoanBookResponseData saveLoanBook(LoanBookRequest loanBookRequest) {
        try {
            return sendSaveLoanBook(loanBookRequest);
        } catch (Exception e) {
            return null;
        }
    }

    private LoanBookResponseData sendSaveLoanBook(LoanBookRequest loanBookRequest) throws Exception {
        // form parameters
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), Utilities.marshalRequest(loanBookRequest));

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return Utilities.unmarshalResponse(response.body(), LoanBookResponseData.class);
        }
    }
}