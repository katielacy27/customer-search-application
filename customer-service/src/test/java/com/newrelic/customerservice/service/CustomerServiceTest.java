package com.newrelic.customerservice.service;

import com.newrelic.customerservice.entity.CustomerEntity;
import com.newrelic.customerservice.model.query.CustomerQuery;
import com.newrelic.customerservice.repository.CustomerRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

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

        Page customerEntityPage = new PageImpl(customerEntityList);
        given(customerRepository.findAll(isA(Pageable.class))).willReturn(customerEntityPage);

        CustomerQuery query = new CustomerQuery();
        List<CustomerEntity> returnList = customerService.getCustomers(query);

        assertThat(returnList.size()).isEqualTo(2);

    }

    @Test
    void givenNoCustomerExists_getCustomersReturnsNoCustomers() {

        List<CustomerEntity> customerEntityList =  Collections.emptyList();

        Page customerEntityPage = new PageImpl(customerEntityList);
        given(customerRepository.findAll(isA(Pageable.class))).willReturn(customerEntityPage);

        CustomerQuery query = new CustomerQuery();
        List<CustomerEntity> returnList = customerService.getCustomers(query);

        assertThat(returnList.size()).isEqualTo(0);

    }
}