package com.bancoserfinanza.models.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerRequest {

    private String firstName;
    private String lastName;
    private String identification;
    private Date dateOfBirth;
}
