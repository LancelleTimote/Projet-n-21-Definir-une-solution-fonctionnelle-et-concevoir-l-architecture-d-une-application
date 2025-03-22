package com.poc.back.payload.response;

import com.poc.back.model.Customer;
import com.poc.back.model.User;
import lombok.Data;

@Data
public class CustomerResponse{
    private User user;
    private Customer customer;

    public CustomerResponse(){}

    public CustomerResponse(User user, Customer customer){
        this.user=user;
        this.customer=customer;
    }
}
