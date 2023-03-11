package com.bancoserfinanza.bookbank.service;

import com.bancoserfinanza.bookbank.dao.CustomerDAO;
import com.bancoserfinanza.models.bookbank.Customer;
import com.bancoserfinanza.models.request.CustomerRequest;
import com.bancoserfinanza.models.response.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public Optional<Customer> save(CustomerRequest customerRequest) {
        return Optional.of(customerDAO.save(customerRequest));
    }

    @Override
    public Optional<Customer> update(CustomerRequest customerRequest) {
        return Optional.of(customerDAO.update(customerRequest));
    }

    @Override
    public void delete(Long customerId) {
        customerDAO.delete(customerId);
    }

    @Override
    public List<CustomerResponse> getCustomers() {
        return customerDAO.getCustomers();
    }
}
