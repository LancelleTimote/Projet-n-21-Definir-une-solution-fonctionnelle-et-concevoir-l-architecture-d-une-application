package com.poc.back.controller;

import com.poc.back.dto.UserDto;
import com.poc.back.model.Customer;
import com.poc.back.model.CustomerSupport;
import com.poc.back.model.User;
import com.poc.back.model.enums.UserType;
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
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);

        if (UserType.CUSTOMER.equals(user.getType())) {
            Customer customer = customerService.findCustomerByUser(user);
            if (customer == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new UserDto(user.getId(), user.getEmail(), user.getPassword()));
        } else if (UserType.CUSTOMER_SUPPORT.equals(user.getType())) {
            CustomerSupport customerSupport = customerSupportService.findByCustomerSupport(user);
            if (customerSupport == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(new UserDto(user.getId(), user.getEmail(), user.getPassword()));
        }

        return ResponseEntity.badRequest().body("Type d'utilisateur inconnu.");
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @MessageMapping("/get_all_customer_support")
    public void getAllCustomerSupportUsers(@Payload Long customerId) {
        List<CustomerSupport> list = customerSupportService.findAllCustomerSupport();
        log.info("Envoi de la liste des agents Customer Support à l'utilisateur {}", customerId);
        template.convertAndSendToUser(customerId.toString(), "/queue/customer_support_list", list);
    }
}
