package com.bancoserfinanza.bookbank.service;

import com.bancoserfinanza.models.bookbank.Customer;
import com.bancoserfinanza.models.request.CustomerRequest;
import com.bancoserfinanza.models.response.BookResponse;
import com.bancoserfinanza.models.response.CustomerResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> save(CustomerRequest bookRequest);
    Optional<Customer> update(CustomerRequest bookRequest);
    void delete(Long customerId);

    List<CustomerResponse> getCustomers();
}
