package com.poc.back.repository;

import com.poc.back.model.Conversation;
import com.poc.back.model.Customer;
import com.poc.back.model.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByCustomer(Customer customer);

    List<Conversation> findByCustomerServiceModel(CustomerService customerService);

    Conversation findByCustomerAndCustomerServiceModel(Customer customer, CustomerService customerService);
}