package com.newrelic.customerservice.repository;

import com.newrelic.customerservice.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>, JpaSpecificationExecutor<CustomerEntity> {
}
