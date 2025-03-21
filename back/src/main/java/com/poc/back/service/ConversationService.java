package com.poc.back.service;

import com.poc.back.model.Conversation;
import com.poc.back.model.Customer;
import com.poc.back.model.CustomerSupport;
import com.poc.back.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {
    @Autowired
    private ConversationRepository conversationRepository;

    public Conversation createConversation(Conversation conversation){
        return this.conversationRepository.save(conversation);
    }

    public Conversation updateConversation(Long id, Conversation conversation){
        conversation.setId(id);
        return this.conversationRepository.save(conversation);
    }

    public Conversation findConversationById(Long id){
        return this.conversationRepository.findById(id).orElse(null);
    }

    public List<Conversation> findAllConversationsCustomer(Customer customer){
        return this.conversationRepository.findByCustomer(customer);
    }

    public List<Conversation> findAllConversationsCustomerSupport(CustomerSupport customerSupport){
        return this.conversationRepository.findByCustomerSupport(customerSupport);
    }
}
