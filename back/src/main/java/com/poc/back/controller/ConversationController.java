package com.poc.back.controller;

import com.poc.back.payload.request.NewConversationRequest;
import com.poc.back.model.Conversation;
import com.poc.back.model.Customer;
import com.poc.back.model.CustomerSupport;
import com.poc.back.model.User;
import com.poc.back.service.ConversationService;
import com.poc.back.service.CustomerService;
import com.poc.back.service.CustomerSupportService;
import com.poc.back.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ConversationService conversationService;
    private final UserService userService;
    private final CustomerService customerService;
    private final CustomerSupportService customerSupportService;
    private final SimpMessagingTemplate template;


    @GetMapping("/all/{id}")
    public ResponseEntity<?> getAllConversationsHttp(@PathVariable("id") Long id){
        User user = this.userService.findUserById(id);
        if(user.getUserType().equals("CUSTOMER")){
            Customer customer = this.customerService.findCustomerByUser(user);
            List<Conversation> list = this.conversationService.findAllConversationsCustomer(customer);
            return ResponseEntity.ok().body(list);
        }else if(user.getUserType().equals("CUSTOMER_SUPPORT")){
            CustomerSupport customerSupport = this.customerSupportService.findByCustomerSupport(user);
            List<Conversation> list= this.conversationService.findAllConversationsCustomerSupport(customerSupport);
            return ResponseEntity.ok().body(list);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createConversation(@RequestBody NewConversationRequest request){
        Customer customer = this.customerService.findCustomerById(request.getCustomerId());
        CustomerSupport customerSupport = this.customerSupportService.findCustomerSupportById(request.getCustomerSupportId());
        Conversation conversation = new Conversation(customer,customerSupport, LocalDateTime.now(),LocalDateTime.now());
        this.conversationService.createConversation(conversation);
        return ResponseEntity.ok().body(conversation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findConversationById(@PathVariable("id") Long id) {
        Conversation conversation = conversationService.findConversationById(id);
        return ResponseEntity.ok().body(conversation);
    }

    @MessageMapping("/create_private_conversation")
    public Conversation createPrivateConversationSocket(@Payload NewConversationRequest request){
        Customer customer = this.customerService.findCustomerById(request.getCustomerId());
        CustomerSupport customerSupport = this.customerSupportService.findCustomerSupportById(request.getCustomerSupportId());
        Conversation conversation = new Conversation(customer,customerSupport, LocalDateTime.now(),LocalDateTime.now());
        this.conversationService.createConversation(conversation);
        this.template.convertAndSendToUser(request.getCustomerSupportId().toString(),"/new_private_conversation/customer_service", conversation);
        this.template.convertAndSendToUser(request.getCustomerId().toString(),"/new_private_conversation/customer", conversation);
        return conversation;
    }
}
