package com.bancoserfinanza.bookbank.dao;

import com.bancoserfinanza.models.bookbank.Customer;
import com.bancoserfinanza.models.request.CustomerRequest;
import com.bancoserfinanza.models.response.CustomerResponse;

import java.util.List;

public interface CustomerDAO {

    Customer save(CustomerRequest customerRequest);
    Customer update(CustomerRequest customerRequest);
    void delete(Long customerId);

    List<CustomerResponse> getCustomers();
}
