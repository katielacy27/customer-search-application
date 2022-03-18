package com.newrelic.customerservice.service.specification;

import com.newrelic.customerservice.entity.CustomerEntity;
import com.newrelic.customerservice.entity.CustomerEntity_;
import com.newrelic.customerservice.model.query.CustomerQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CustomerSpecification {

    // we don't care if the first name or last name begins with the search query
    // thus we use specification.or
    public Specification<CustomerEntity> getSpecificationFromQuery(CustomerQuery query) {
        Specification<CustomerEntity> returned = Specification.where(containsFirstName(query.getName()))
                .or(containsLastName(query.getName()))
                .and(hasCompanyName(query.getCompanyName()));
        return returned;
    }

    public Specification<CustomerEntity> containsFirstName(String firstName) {
        if (firstName == null) return null;
        // criteria builder substring signature: substring(phrase, start, length);
        // find if the first characters of the name match what we are searching
        return (customerEntityRoot, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(
                        criteriaBuilder.substring(customerEntityRoot.get(CustomerEntity_.firstName),1, firstName.length())
                        , firstName);    }

    public Specification<CustomerEntity> containsLastName(String lastName) {
        if (lastName == null) return null;
        // criteria builder substring signature: substring(phrase, start, length);
        // find if the first characters of the name match what we are searching
        return (customerEntityRoot, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(
                        criteriaBuilder.substring(customerEntityRoot.get(CustomerEntity_.lastName),1, lastName.length())
                        , lastName);
    }

    public Specification<CustomerEntity> hasCompanyName(String companyName) {
        if (companyName == null) return null;
        return (customerEntityRoot, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(customerEntityRoot.get(CustomerEntity_.companyName), companyName);
    }
}
