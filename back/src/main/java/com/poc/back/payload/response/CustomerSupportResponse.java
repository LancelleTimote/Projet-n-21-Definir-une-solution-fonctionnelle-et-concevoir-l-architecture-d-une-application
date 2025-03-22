package com.poc.back.payload.response;

import com.poc.back.model.CustomerSupport;
import com.poc.back.model.User;
import lombok.Data;

@Data
public class CustomerSupportResponse {
    private User user;
    private CustomerSupport customerSupport;

    public CustomerSupportResponse(){}

    public CustomerSupportResponse(User user, CustomerSupport customerSupport) {
        this.user = user;
        this.customerSupport = customerSupport;
    }
}
