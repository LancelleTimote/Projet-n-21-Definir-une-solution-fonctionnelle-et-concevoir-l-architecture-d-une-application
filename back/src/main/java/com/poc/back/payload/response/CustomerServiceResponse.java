package com.poc.back.payload.response;

import com.poc.back.models.CustomerServiceModel;
import com.poc.back.models.User;
import lombok.Data;

@Data
public class CustomerServiceResponse{
    private User user;

    private CustomerServiceModel customerServiceModel;

    public CustomerServiceResponse(){}

    public CustomerServiceResponse(User user,CustomerServiceModel customerServiceModel) {
        this.user=user;
        this.customerServiceModel=customerServiceModel;
    }
}
