package com.newrelic.customerservice.service.specification;

import com.newrelic.customerservice.entity.CustomerEntity;
import com.newrelic.customerservice.model.query.CustomerQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CustomerSpecification {
    // I wrote this code intending to be querying the ENTIRE database of customers rather than
    // just returning a page of 50 customers and filtering on THAT data. I reread the question
    // and realized that filtering on the page is what was being asked.
    // I am keeping this for if I wanted to search that in the FUTURE
    public Specification<CustomerEntity> getSpecificationFromQuery(CustomerQuery query) {
        return null;
    }
}
