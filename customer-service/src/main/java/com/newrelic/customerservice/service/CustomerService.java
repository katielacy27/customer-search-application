package com.newrelic.customerservice.service;

import com.newrelic.customerservice.entity.CustomerEntity;
import com.newrelic.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> getCustomers() {
        return customerRepository.findAll();
    }
}
