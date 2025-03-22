package com.poc.back.controller;

import com.poc.back.model.Customer;
import com.poc.back.model.CustomerSupport;
import com.poc.back.model.User;
import com.poc.back.payload.response.CustomerResponse;
import com.poc.back.payload.response.CustomerSupportResponse;
import com.poc.back.service.CustomerService;
import com.poc.back.service.CustomerSupportService;
import com.poc.back.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final CustomerService customerService;
    private final CustomerSupportService customerSupportService;
    private final SimpMessagingTemplate template;


    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id){
        User user = this.userService.findUserById(id);
        if(user.getUserType().equals("CUSTOMER")){
            Customer customer = this.customerService.findCustomerByUser(user);
            CustomerResponse customerResponse = new CustomerResponse(user,customer);
            return ResponseEntity.ok().body(customerResponse);
        } else if (user.getUserType().equals("CUSTOMER_SUPPORT")) {
            CustomerSupport customerSupport = this.customerSupportService.findByCustomerSupport(user);
            CustomerSupportResponse customerServiceResponse = new CustomerSupportResponse(user,customerSupport);
            return ResponseEntity.ok().body(customerServiceResponse);
        }
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers(){
        List<User> users = this.userService.findAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @MessageMapping("/get_all_customer_support_users")
    public List<CustomerSupport> getAllCustomerSupportUsersTest(@Payload Long customerId){
        List<CustomerSupport> list = this.customerSupportService.findAllCustomerSupport();
        this.template.convertAndSendToUser(customerId.toString(),"/get_all_customer_support_users", list);
        return list;
    }
}
