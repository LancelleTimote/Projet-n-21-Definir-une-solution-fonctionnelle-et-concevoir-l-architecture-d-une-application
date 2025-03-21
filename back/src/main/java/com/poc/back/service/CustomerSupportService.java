package com.poc.back.service;

import com.poc.back.model.CustomerSupport;
import com.poc.back.model.User;
import com.poc.back.repository.CustomerSupportRepository;
import com.poc.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerSupportService {
    @Autowired
    private CustomerSupportRepository customerSupportRepository;

    @Autowired
    private UserRepository userRepository;

    public CustomerSupport findCustomerSupportById(Long id){
        return this.customerSupportRepository.findById(id).orElse(null);
    }

    public CustomerSupport findByCustomerSupport(User user){
        return this.customerSupportRepository.findByCustomerSupport(user);
    }

    public List<CustomerSupport> findAllCustomerSupport(){
        return this.customerSupportRepository.findAll();
    }

    public CustomerSupport findUserByCustomerSupportId(Long id){
        return this.customerSupportRepository.findByCustomerSupportId(id);
    }
}
