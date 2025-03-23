package com.poc.back.controller;

import com.poc.back.models.CustomerServiceModel;
import com.poc.back.services.CustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer_service")
public class CustomerServiceController {
    @Autowired
    private CustomerServiceService customerServiceService;

    @GetMapping()
    public ResponseEntity<?> getAllCustomerServiceUsers(){
        List<CustomerServiceModel> customerServiceModelList = this.customerServiceService.findAllCustomerService();
        return ResponseEntity.ok().body(customerServiceModelList);
    }
}
