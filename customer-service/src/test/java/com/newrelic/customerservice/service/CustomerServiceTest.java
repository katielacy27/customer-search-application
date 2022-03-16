package com.newrelic.customerservice.service;

import com.newrelic.customerservice.entity.CustomerEntity;
import com.newrelic.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

        given(customerRepository.findAll()).willReturn(customerEntityList);

        List<CustomerEntity> returnList = customerService.getCustomers();
        assertThat(returnList).isSameAs(customerEntityList);

    }

    @Test
    void givenNoCustomerExists_getCustomersReturnsNoCustomers() {

        List<CustomerEntity> customerEntityList =  Collections.emptyList();

        given(customerRepository.findAll()).willReturn(customerEntityList);

        List<CustomerEntity> returnList = customerService.getCustomers();
        assertThat(returnList).isSameAs(customerEntityList);

    }
}