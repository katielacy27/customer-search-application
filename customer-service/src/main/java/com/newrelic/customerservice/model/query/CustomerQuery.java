package com.newrelic.customerservice.model.query;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerQuery {
    private String firstName;
    private String lastName;
    private String companyName;
}
