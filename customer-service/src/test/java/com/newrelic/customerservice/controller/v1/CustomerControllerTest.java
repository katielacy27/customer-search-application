package com.newrelic.customerservice.controller.v1;

import com.newrelic.customerservice.entity.CustomerEntity;
import com.newrelic.customerservice.model.query.CustomerQuery;
import com.newrelic.customerservice.service.CustomerService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @Test
    @SneakyThrows
    void getCustomersReturnsSingleCustomer() {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .firstName("George Michael")
                .lastName("Bluth")
                .companyName("The Banana Stand")
                .build();

        when(customerService.getCustomers(isA(CustomerQuery.class))).thenReturn(Collections.singletonList(customerEntity));
        mvc.perform(MockMvcRequestBuilders.get("/v1/customers/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].firstName", containsInAnyOrder("George Michael")));

    }

    @Test
    @SneakyThrows
    void getCustomersReturnsManyCustomers() {
        List<CustomerEntity> customerEntityList = new ArrayList<>();

        CustomerEntity customerEntity = CustomerEntity.builder()
                .firstName("George Michael")
                .lastName("Bluth")
                .companyName("The Banana Stand")
                .build();
        customerEntityList.add(customerEntity);

        CustomerEntity customerEntity2 = CustomerEntity.builder()
                .firstName("Maeby")
                .lastName("Funke")
                .companyName("Tantamount Studios")
                .build();
        customerEntityList.add(customerEntity2);

        CustomerEntity customerEntity3 = CustomerEntity.builder()
                .firstName("Steve")
                .lastName("Holt")
                .companyName("Steve Holt! Pest Control")
                .build();
        customerEntityList.add(customerEntity3);

        when(customerService.getCustomers(isA(CustomerQuery.class))).thenReturn(customerEntityList);
        mvc.perform(MockMvcRequestBuilders.get("/v1/customers/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].firstName", containsInAnyOrder("Maeby", "Steve", "George Michael")));

    }

    @Test
    @SneakyThrows
    void getCustomersReturnsNoCustomers() {
        when(customerService.getCustomers(isA(CustomerQuery.class))).thenReturn(Collections.emptyList());
        mvc.perform(MockMvcRequestBuilders.get("/v1/customers/"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

    }
}
