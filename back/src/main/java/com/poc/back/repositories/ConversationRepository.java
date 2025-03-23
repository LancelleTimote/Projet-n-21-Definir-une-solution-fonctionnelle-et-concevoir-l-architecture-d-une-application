package com.poc.back.repositories;

;
import com.poc.back.models.Conversation;
import com.poc.back.models.Customer;
import com.poc.back.models.CustomerServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByCustomer(Customer customer);

    List<Conversation> findByCustomerServiceModel(CustomerServiceModel customerServiceModel);

    Conversation findByCustomerAndCustomerServiceModel(Customer customer, CustomerServiceModel customerServiceModel);
}
