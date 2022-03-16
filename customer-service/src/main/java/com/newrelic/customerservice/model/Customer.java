package com.newrelic.customerservice.model;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// NEXT STPES: ideally I would like to put this class in a seperate package
// like customer-service-sdk. That way another applicaiton could use this model without
// having to import the rest of the service code

@Getter
@Setter
@Builder
public class Customer {
    private String firstName;
    private String lastName;
    private String companyName;
}
