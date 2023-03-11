package com.bancoserfinanza.bookbank.controller;

import com.bancoserfinanza.bookbank.exception.CustomerNotCreatedException;
import com.bancoserfinanza.bookbank.service.CustomerService;
import com.bancoserfinanza.models.bookbank.Customer;
import com.bancoserfinanza.models.enums.ResponseCodes;
import com.bancoserfinanza.models.request.CustomerRequest;
import com.bancoserfinanza.models.response.AutoCompleteSuggestion;
import com.bancoserfinanza.models.response.AutoCompleteSuggestionResponse;
import com.bancoserfinanza.models.response.CustomerResponse;
import com.bancoserfinanza.models.response.DataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/v0")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse<CustomerResponse>> createCustomer(@Valid @RequestBody final CustomerRequest customerRequest) {
        DataResponse response = new DataResponse();
        logger.info("Creating new customer");
        logger.info("Request received {}", customerRequest);

        Optional<Customer> customer = customerService.save(customerRequest);

        if (!customer.isPresent()) {
            throw new CustomerNotCreatedException();
        }

        response.setCode(ResponseCodes.OK.getCode());
        response.setData(CustomerResponse.build(customer.get()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse<CustomerResponse>> updateCustomer(@Valid @RequestBody final CustomerRequest customerRequest) {
        DataResponse response = new DataResponse();
        logger.debug("Updating new customer");
        logger.debug("Request received {}", customerRequest);

        Optional<Customer> customer = customerService.update(customerRequest);

        if (!customer.isPresent()) {
            throw new CustomerNotCreatedException();
        }

        response.setCode(ResponseCodes.OK.getCode());
        response.setData(CustomerResponse.build(customer.get()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse<Long>> deleteCustomer(@PathVariable Long customerId) {
        DataResponse response = new DataResponse();
        logger.debug("Deleting customer {}", customerId);

        customerService.delete(customerId);

        response.setCode(ResponseCodes.OK.getCode());
        response.setData(customerId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataResponse<CustomerResponse>> getCustomers() {
        DataResponse response = new DataResponse();
        logger.debug("Getting customers");

        List<CustomerResponse> customerResponseList = customerService.getCustomers();

        response.setCode(ResponseCodes.OK.getCode());
        response.setData(customerResponseList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/customer/autocomplete", produces = MediaType.APPLICATION_JSON_VALUE)
    public AutoCompleteSuggestionResponse autocomplete(@RequestParam(value = "query", required = true, defaultValue = "") String query) {
        DataResponse response = new DataResponse();
        logger.debug("Getting customers");

        List<CustomerResponse> customerResponseList = customerService.getCustomers();
        String queryLowerCase = query.toLowerCase();

        return new AutoCompleteSuggestionResponse(customerResponseList.stream()
                .filter(t -> t.getFirstName().toLowerCase().contains(queryLowerCase))
                .map(t -> new AutoCompleteSuggestion().setLabel(t.getFirstName() + " " + t.getLastName())
                        .setValue(marshalRequest(t)).setRawValue(t))
                .collect(Collectors.toList()));
    }

    public static String marshalRequest(Object request)  {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
