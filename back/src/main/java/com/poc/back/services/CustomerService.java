package com.poc.back.services;

import com.poc.back.models.Customer;
import com.poc.back.models.User;
import com.poc.back.repositories.CustomerRepository;
import com.poc.back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public Customer findCustomerById(Long id){
        return this.customerRepository.findById(id).orElse(null);
    }

    public Customer findByCustomerId(Long id){
        return this.customerRepository.findByCustomerid(id);
    }

    public List<Customer> findAllCustomers(){
        return this.customerRepository.findAll();
    }

    public Customer findCustomerByUser(User user){
        return this.customerRepository.findByCustomer(user);
    }
}
