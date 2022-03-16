package com.newrelic.customerservice.model;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// NEXT STEPS: ideally I would like to put this class in a separate package
// like customer-service-sdk. That way another application could use this model without
// having to import the rest of the service code

@Getter
@Setter
@Builder
public class Customer {
    private String firstName;
    private String lastName;
    private String companyName;
}
