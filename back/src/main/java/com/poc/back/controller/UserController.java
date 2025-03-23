package com.poc.back.controller;

import com.poc.back.models.Customer;
import com.poc.back.models.CustomerServiceModel;
import com.poc.back.models.User;
import com.poc.back.payload.response.CustomerResponse;
import com.poc.back.payload.response.CustomerServiceResponse;
import com.poc.back.services.CustomerService;
import com.poc.back.services.CustomerServiceService;
import com.poc.back.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerServiceService customerServiceService;

    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id){
        User user = this.userService.findUserById(id);
        if(user.getUsertype().equals("customer")){
            Customer customer = this.customerService.findCustomerByUser(user);
            CustomerResponse customerResponse = new CustomerResponse(user,customer);
            return ResponseEntity.ok().body(customerResponse);
        } else if (user.getUsertype().equals("customer_service")) {
            CustomerServiceModel customerServiceModel1 = this.customerServiceService.findByCustomerService(user);
            CustomerServiceResponse customerServiceResponse = new CustomerServiceResponse(user,customerServiceModel1);
            return ResponseEntity.ok().body(customerServiceResponse);
        }
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers(){
        List<User> users = this.userService.findAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @MessageMapping("/get_all_customer_service_users")
    public List<CustomerServiceModel> getAllCustomerServiceUsersTest(@Payload Long customerId){
        List<CustomerServiceModel> list = this.customerServiceService.findAllCustomerService();
        this.template.convertAndSendToUser(customerId.toString(),"/get_all_customer_service_users", list);
        return list;
    }
}
