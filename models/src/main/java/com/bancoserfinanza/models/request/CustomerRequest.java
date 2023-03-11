package com.bancoserfinanza.models.request;

import com.bancoserfinanza.models.bookbank.Customer;
import com.bancoserfinanza.models.response.BookResponse;
import com.bancoserfinanza.models.response.CustomerResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerRequest {
    private Long id;

    private String firstName;
    private String lastName;
    private String identification;
    private Date dateOfBirth;

    public static Customer build(CustomerResponse customer) {
        Customer customerRequest = new Customer();
        customerRequest.setId(customer.getId());
        customerRequest.setFirstName(customer.getFirstName());
        customerRequest.setLastName(customer.getLastName());
        customerRequest.setIdentification(customer.getIdentification());
        customerRequest.setDateOfBirth(customer.getDateOfBirth());

        return customerRequest;
    }
}
