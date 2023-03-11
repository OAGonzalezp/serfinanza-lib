package com.bancoserfinanza.bookbank.controller;

import com.bancoserfinanza.bookbank.util.Utilities;
import com.bancoserfinanza.models.request.CustomerRequest;
import com.bancoserfinanza.models.response.CustomerListResponseData;
import com.bancoserfinanza.models.response.CustomerResponse;
import com.bancoserfinanza.models.response.CustomerResponseData;
import okhttp3.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class CustomerService {

    public static final String API_URL = "http://localhost:8080/v0/customer";

    private final OkHttpClient httpClient = new OkHttpClient();

    @PostConstruct
    public void init() {
    }


    public List<CustomerResponse> loadCustomers() {
        try {
            return sendGetCustomers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<CustomerResponse> sendGetCustomers() throws Exception {
        List<CustomerResponse> customers = new ArrayList<>();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            CustomerListResponseData responseData = Utilities.unmarshalResponse(response.body(), CustomerListResponseData.class);
            if (responseData.getCode() == 1) {
                customers = responseData.getData();
            } else {
                customers = new ArrayList<>();
            }
        }

        return customers;
    }

    public CustomerResponse updateCustomer(CustomerRequest customerRequest) {
        try {
            return sendUpdateCustomer(customerRequest);
        } catch (Exception e) {
            return null;
        }
    }

    private CustomerResponse sendUpdateCustomer(CustomerRequest customerRequest) throws Exception {
        // form parameters
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), Utilities.marshalRequest(customerRequest));

        Request request = new Request.Builder()
                .url(API_URL)
                .put(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            CustomerResponseData responseData = Utilities.unmarshalResponse(response.body(), CustomerResponseData.class);
            if (responseData.getCode() == 1) {
                return responseData.getData();
            } else {
                return null;
            }
        }
    }

    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        try {
            return sendSaveCustomer(customerRequest);
        } catch (Exception e) {
            return null;
        }
    }

    private CustomerResponse sendSaveCustomer(CustomerRequest customerRequest) throws Exception {
        // form parameters
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), Utilities.marshalRequest(customerRequest));

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            CustomerResponseData responseData = Utilities.unmarshalResponse(response.body(), CustomerResponseData.class);
            if (responseData.getCode() == 1) {
                return responseData.getData();
            } else {
                return null;
            }
        }
    }
}