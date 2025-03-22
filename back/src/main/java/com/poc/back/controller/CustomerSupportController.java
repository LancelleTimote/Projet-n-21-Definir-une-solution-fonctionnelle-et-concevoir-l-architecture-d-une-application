package com.poc.back.controller;

import com.poc.back.model.CustomerSupport;
import com.poc.back.service.CustomerSupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer_support")
public class CustomerSupportController {
    private final CustomerSupportService customerSupportService;

    @GetMapping()
    public ResponseEntity<?> getAllCustomerSupportUsers(){
        List<CustomerSupport> customerSupportList = this.customerSupportService.findAllCustomerSupport();
        return ResponseEntity.ok().body(customerSupportList);
    }
}
