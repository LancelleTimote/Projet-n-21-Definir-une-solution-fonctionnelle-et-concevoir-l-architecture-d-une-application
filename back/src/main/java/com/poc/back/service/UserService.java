package com.poc.back.service;

import com.poc.back.model.User;
import com.poc.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User findUserById(Long id){
        return this.userRepository.findById(id).orElse(null);
    }

    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }
}
