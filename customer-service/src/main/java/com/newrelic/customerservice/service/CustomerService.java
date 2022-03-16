package com.newrelic.customerservice.service;

import com.newrelic.customerservice.entity.CustomerEntity;
import com.newrelic.customerservice.model.query.CustomerQuery;
import com.newrelic.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // I added a Customer Query here thinking I had to filter / query in the backend but after
    // rereading the question it looks like the filtering is all done in the frontend and data is
    // pre sorted in the backend
    // im pretty sure this is how AWS filtering works when searching in ECS... I'm going to
    // leave the query object in here unused in case I want to actually query data in the future!
    public List<CustomerEntity> getCustomers(CustomerQuery customerQuery) {
        Pageable limit = PageRequest.of(0,50);
        return customerRepository.findAll(limit).getContent();
    }
}
