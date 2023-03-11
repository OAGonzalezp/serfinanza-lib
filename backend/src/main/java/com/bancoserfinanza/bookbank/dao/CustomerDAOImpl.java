package com.bancoserfinanza.bookbank.dao;

import com.bancoserfinanza.bookbank.repository.ICustomerRepository;
import com.bancoserfinanza.models.bookbank.Customer;
import com.bancoserfinanza.models.request.CustomerRequest;
import com.bancoserfinanza.models.response.CustomerResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    ICustomerRepository iCustomerRepository;

    @Override
    public Customer save(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequest, customer);

        return iCustomerRepository.save(customer);
    }

    @Override
    public Customer update(CustomerRequest customerRequest) {
        Customer customer = iCustomerRepository.getById(customerRequest.getId());
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        BeanUtils.copyProperties(customerRequest, customer, "id");

        return iCustomerRepository.save(customer);
    }

    @Override
    public void delete(Long customerId) {
        Customer customer = iCustomerRepository.getById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        iCustomerRepository.delete(customer);
    }

    @Override
    public List<CustomerResponse> getCustomers() {
        List<Customer> customers = iCustomerRepository.findAll();
        return customers.stream().map(x -> CustomerResponse.build(x)).collect(Collectors.toList());
    }
}
