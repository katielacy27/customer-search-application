package com.newrelic.customerservice.model.query;

import com.newrelic.customerservice.model.enums.SortBy;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerQuery {
    private String name;
    private String companyName;
    private SortBy sortBy;
}
