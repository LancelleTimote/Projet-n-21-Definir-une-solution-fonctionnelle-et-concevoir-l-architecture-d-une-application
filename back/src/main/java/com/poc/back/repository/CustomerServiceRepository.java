package com.poc.back.repository;

import com.poc.back.model.CustomerService;
import com.poc.back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerServiceRepository extends JpaRepository<CustomerService, Long> {
    CustomerService findByCustomerservice(User user);

    CustomerService findByCustomerserviceid(Long id);
}
