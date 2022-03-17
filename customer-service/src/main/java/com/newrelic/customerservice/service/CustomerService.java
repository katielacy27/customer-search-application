package com.newrelic.customerservice.service;

import com.newrelic.customerservice.entity.CustomerEntity;
import com.newrelic.customerservice.model.query.CustomerQuery;
import com.newrelic.customerservice.repository.CustomerRepository;
import com.newrelic.customerservice.service.specification.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static com.newrelic.customerservice.model.enums.SortBy.FIRST_NAME_ASCENDING;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerSpecification specification;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerSpecification specification) {
        this.customerRepository = customerRepository;
        this.specification = specification;
    }

    public List<CustomerEntity> getCustomers(CustomerQuery customerQuery) {
        Specification<CustomerEntity> customerEntitySpecification = specification.getSpecificationFromQuery(customerQuery);
        List<CustomerEntity> customerList = customerRepository.findAll(customerEntitySpecification);

        // now that we have a list of customers we want to sort them based on our sortBy enum
        // if it doesn't exist we default to first name ascending
        switch((customerQuery.getSortBy()!= null) ? customerQuery.getSortBy() : FIRST_NAME_ASCENDING ) {
            case FIRST_NAME_ASCENDING:
                customerList.sort(Comparator.comparing(CustomerEntity::getFirstName));
                break;
            case LAST_NAME_ASCENDING:
                customerList.sort(Comparator.comparing(CustomerEntity::getLastName));
                break;
            case COMPANY_NAME_ASCENDING:
                customerList.sort(Comparator.comparing(CustomerEntity::getCompanyName));
                break;
            case FIRST_NAME_DESCENDING:
                customerList.sort(Comparator.comparing(CustomerEntity::getFirstName).reversed());
                break;
            case LAST_NAME_DESCENDING:
                customerList.sort(Comparator.comparing(CustomerEntity::getLastName).reversed());
                break;
            case COMPANY_NAME_DESCENDING:
                customerList.sort(Comparator.comparing(CustomerEntity::getCompanyName).reversed());
                break;
        }
        return customerList;
    }
}
