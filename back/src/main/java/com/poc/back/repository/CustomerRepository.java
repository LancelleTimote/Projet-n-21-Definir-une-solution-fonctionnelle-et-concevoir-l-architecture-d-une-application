package com.poc.back.repository;

import com.poc.back.model.Customer;
import com.poc.back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomer(User user);
    Customer findByCustomerid(Long id);
}
