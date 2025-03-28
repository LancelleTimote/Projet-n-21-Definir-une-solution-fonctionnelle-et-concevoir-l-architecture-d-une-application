package com.poc.back.controller;


import com.poc.back.models.Chat;
import com.poc.back.models.Conversation;
import com.poc.back.payload.request.NewChatRequest;
import com.poc.back.services.ChatService;
import com.poc.back.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("/{id}")
    public ResponseEntity<?> getChatMessages(@PathVariable Long id){
        Conversation conversation = this.conversationService.findConversationById(id);
        List<Chat> chatList = this.chatService.findAllByConversation(conversation);
        return ResponseEntity.ok().body(chatList);
    }

    @MessageMapping("/get_chat_messages/{conversationId}")
    @SendTo("/topic/getMessages/{conversationId}")
    public List<Chat> getChatMessagesSocket(@Payload @DestinationVariable Long conversationId){
        Conversation conversation = this.conversationService.findConversationById(conversationId);
        List<Chat> messages = this.chatService.findAllByConversation(conversation);
        return messages;
    }

    @MessageMapping("/sendMessage/{conversationId}")
    @SendTo("/topic/message_sent/{conversationId}")
    public Chat sendChatMessageSocket(@Payload Chat messageRequest, @DestinationVariable Long conversationId) {
        Conversation conversation = conversationService.findConversationById(conversationId);
        Chat chat = new Chat(conversation, messageRequest.getUser(), messageRequest.getMessage(), LocalDateTime.now());
        chatService.sendChatMessage(chat);
        return chat;
    }

    @MessageMapping("/sendNewMessage")
    public Chat sendNewMessageSocket(@Payload NewChatRequest messageRequest) {
        Conversation conversation = conversationService.findConversationById(messageRequest.getConversationid());
        Chat chat = new Chat(conversation, messageRequest.getUser(), messageRequest.getMessage(), LocalDateTime.now());
        chatService.sendChatMessage(chat);

        template.convertAndSendToUser(
                conversation.getCustomer().getId().toString(),
                "/conversation/customer/" + messageRequest.getConversationid(),
                chat
        );

        template.convertAndSendToUser(
                conversation.getCustomerServiceModel().getId().toString(),
                "/conversation/customer_service/" + messageRequest.getConversationid(),
                chat
        );

        return chat;
    }
}
