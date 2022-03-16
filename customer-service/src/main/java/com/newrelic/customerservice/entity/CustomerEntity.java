package com.newrelic.customerservice.entity;
import com.newrelic.customerservice.model.Customer;

import lombok.*;

import javax.persistence.*;

@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String companyName;

    // DTO translation 
    public static Customer toCustomerModel(CustomerEntity customerEntity) {
        return Customer.builder()
            .firstName(customerEntity.getFirstName())
            .lastName(customerEntity.getLastName())
            .companyName(customerEntity.getCompanyName())
            .build();
    }
}
