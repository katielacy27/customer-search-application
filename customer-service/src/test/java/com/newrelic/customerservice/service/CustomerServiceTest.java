package com.newrelic.customerservice.service;

import com.newrelic.customerservice.entity.CustomerEntity;
import com.newrelic.customerservice.model.query.CustomerQuery;
import com.newrelic.customerservice.repository.CustomerRepository;
import com.newrelic.customerservice.service.specification.CustomerSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerSpecification customerSpecification;

    @Mock
    private Specification<CustomerEntity> mockSpecification;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void givenCustomersExist_getCustomersReturnsCustomers() {
        List<CustomerEntity> customerEntityList = new ArrayList<>();
        CustomerEntity customerEntity = CustomerEntity.builder()
                .firstName("Tobias")
                .lastName("Funke")
                .companyName("Gobias Industries")
                .build();
        customerEntityList.add(customerEntity);

        CustomerEntity customerEntity2 = CustomerEntity.builder()
                .firstName("Gob")
                .lastName("Bluth")
                .companyName("Gobias Industries")
                .build();
        customerEntityList.add(customerEntity2);

        when(customerSpecification.getSpecificationFromQuery(any())).thenReturn(mockSpecification);
        given(customerRepository.findAll(eq(mockSpecification))).willReturn(customerEntityList);

        CustomerQuery query = new CustomerQuery();
        List<CustomerEntity> returnList = customerService.getCustomers(query);

        assertThat(returnList.size()).isEqualTo(2);

    }

    @Test
    void givenNoCustomerExists_getCustomersReturnsNoCustomers() {

        List<CustomerEntity> customerEntityList =  Collections.emptyList();

        when(customerSpecification.getSpecificationFromQuery(any())).thenReturn(mockSpecification);
        given(customerRepository.findAll(mockSpecification)).willReturn(customerEntityList);

        CustomerQuery query = new CustomerQuery();
        List<CustomerEntity> returnList = customerService.getCustomers(query);

        assertThat(returnList.size()).isEqualTo(0);

    }
}