package com.poc.back.repository;

import com.poc.back.model.CustomerSupport;
import com.poc.back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerSupportRepository extends JpaRepository<CustomerSupport, Long> {
    CustomerSupport findByCustomerSupport(User user);

    CustomerSupport findByCustomerSupportId(Long id);
}
