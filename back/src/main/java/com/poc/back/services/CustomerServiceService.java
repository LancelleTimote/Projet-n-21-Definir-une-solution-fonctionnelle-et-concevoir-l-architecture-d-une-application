package com.poc.back.services;

import com.poc.back.models.CustomerServiceModel;
import com.poc.back.models.User;
import com.poc.back.repositories.CustomerServiceRepository;
import com.poc.back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceService {
    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    @Autowired
    private UserRepository userRepository;

    public CustomerServiceModel findCustomerServiceById(Long id){
        return this.customerServiceRepository.findById(id).orElse(null);
    }

    public CustomerServiceModel findByCustomerService(User user){
        return this.customerServiceRepository.findByCustomerservice(user);
    }

    public List<CustomerServiceModel> findAllCustomerService(){
        return this.customerServiceRepository.findAll();
    }

    public CustomerServiceModel findUserByCustomerServiceId(Long id){
        return this.customerServiceRepository.findByCustomerserviceid(id);
    }
}
