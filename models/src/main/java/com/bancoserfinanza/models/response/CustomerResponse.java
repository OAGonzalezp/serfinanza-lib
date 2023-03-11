package com.bancoserfinanza.models.response;

import com.bancoserfinanza.models.bookbank.Book;
import com.bancoserfinanza.models.bookbank.Customer;
import com.bancoserfinanza.models.request.CustomerRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String identification;
    private Date dateOfBirth;


    public static CustomerResponse build(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setFirstName(customer.getFirstName());
        customerResponse.setLastName(customer.getLastName());
        customerResponse.setIdentification(customer.getIdentification());
        customerResponse.setDateOfBirth(customer.getDateOfBirth());

        return customerResponse;
    }


    public static CustomerResponse build(CustomerRequest customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setFirstName(customer.getFirstName());
        customerResponse.setLastName(customer.getLastName());
        customerResponse.setIdentification(customer.getIdentification());
        customerResponse.setDateOfBirth(customer.getDateOfBirth());

        return customerResponse;
    }
}
