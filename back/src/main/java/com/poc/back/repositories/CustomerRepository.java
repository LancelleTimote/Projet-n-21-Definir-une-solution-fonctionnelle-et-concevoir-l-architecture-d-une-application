package com.poc.back.repositories;

import com.poc.back.models.Customer;
import com.poc.back.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByCustomer(User user);

    Customer findByCustomer_Id(Long id);
}
