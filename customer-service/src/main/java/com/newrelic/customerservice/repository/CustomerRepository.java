package com.newrelic.customerservice.repository;

import com.newrelic.customerservice.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    Page<CustomerEntity> findAll(Pageable pageable);

}
