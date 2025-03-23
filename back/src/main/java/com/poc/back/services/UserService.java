package com.poc.back.services;

import com.poc.back.models.User;
import com.poc.back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }

    public User findUserById(Long id){
        return this.userRepository.findById(id).orElse(null);
    }

    public User create(User user){
        return this.userRepository.save(user);
    }
}
