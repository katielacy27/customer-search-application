package com.newrelic.customerservice.controller.v1;

import com.newrelic.customerservice.entity.CustomerEntity;
import com.newrelic.customerservice.model.Customer;
import com.newrelic.customerservice.model.query.CustomerQuery;
import com.newrelic.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value= "/v1")
public class CustomerController {
  private final CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
      this.customerService = customerService;
  }

  // Maybe we want to query the customers instead of returning all of them
  // That is why I versioned this endpoint to v1 since we may change it later
  // This will also be useful if and when I want to add Pagination or querying
  @GetMapping(value= "/customers/", produces= MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<Customer> getCustomers() {
    // use a DTO since it reduces the amount of data that needs to be sent
    // to our front end, and it makes a great model :)

    // I have left the CustomerQuery here as just an empty object
    return customerService.getCustomers(new CustomerQuery()).stream().
            map(CustomerEntity::toCustomerModel)
            .collect(Collectors.toList());
  }
}
