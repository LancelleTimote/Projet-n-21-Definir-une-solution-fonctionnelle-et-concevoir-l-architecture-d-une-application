package com.poc.back.controller;

import com.poc.back.payload.NewConversationRequest;
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

import java.security.Principal;
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
    public ResponseEntity<?> getAllConversationsHttp(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Conversation> conversations;
        if ("customer".equals(user.getType())) {
            Customer customer = customerService.findCustomerByUser(user);
            conversations = conversationService.findAllConversationsCustomer(customer);
        } else if ("customer_support".equals(user.getType())) {
            CustomerSupport customerSupport = customerSupportService.findByCustomerSupport(user);
            conversations = conversationService.findAllConversationsCustomerSupport(customerSupport);
        } else {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(conversations);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createConversation(@RequestBody NewConversationRequest request) {
        Customer customer = customerService.findCustomerById(request.getCustomerId());
        CustomerSupport customerSupport = customerSupportService.findCustomerSupportById(request.getCustomerSupportId());

        if (customer == null || customerSupport == null) {
            return ResponseEntity.badRequest().body("Client ou support client introuvable.");
        }

        Conversation conversation = new Conversation(customer, customerSupport, LocalDateTime.now(), LocalDateTime.now());
        conversationService.createConversation(conversation);

        return ResponseEntity.ok().body(conversation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findConversationById(@PathVariable("id") Long id) {
        Conversation conversation = conversationService.findConversationById(id);
        return ResponseEntity.ok().body(conversation);
    }

    @MessageMapping("/create_private_conversation")
    public void createPrivateConversationSocket(@Payload NewConversationRequest request, Principal principal) {
        log.info("Création d'une conversation privée entre {} et {}", request.getCustomerId(), request.getCustomerSupportId());

        Customer customer = customerService.findCustomerById(request.getCustomerId());
        CustomerSupport customerSupport = customerSupportService.findCustomerSupportById(request.getCustomerSupportId());

        if (customer == null || customerSupport == null) {
            log.error("Impossible de créer la conversation : Client ou support client introuvable.");
            return;
        }

        if (!principal.getName().equals(customer.getCustomer().getEmail()) &&
                !principal.getName().equals(customerSupport.getCustomerSupport().getEmail())) {
            log.warn("Utilisateur non autorisé à créer cette conversation.");
            return;
        }

        Conversation conversation = new Conversation(customer, customerSupport, LocalDateTime.now(), LocalDateTime.now());
        conversationService.createConversation(conversation);

        template.convertAndSendToUser(request.getCustomerId().toString(), "/new_private_conversation/customer", conversation);
        template.convertAndSendToUser(request.getCustomerSupportId().toString(), "/new_private_conversation/customer_support", conversation);

        log.info("Conversation privée créée avec succès.");
    }
}
