package com.poc.back.controller;

import com.poc.back.payload.request.NewChatRequest;
import com.poc.back.model.Chat;
import com.poc.back.model.Conversation;
import com.poc.back.service.ChatService;
import com.poc.back.service.ConversationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final ConversationService conversationService;
    private final SimpMessagingTemplate messagingTemplate;

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
    public Chat sendChatMessageSocket(@Payload Chat messageRequest,@DestinationVariable Long conversationId){
        Conversation conversation = this.conversationService.findConversationById(conversationId);
        Chat chat = new Chat(conversation,messageRequest.getUser(), messageRequest.getMessage(), LocalDateTime.now(), LocalDateTime.now());
        this.chatService.sendChatMessage(chat);
        conversation.setUpdatedAt(LocalDateTime.now());
        this.conversationService.updateConversation(conversationId, conversation);
        return chat;
    }

    @MessageMapping("/sendNewMessage")
    public Chat sendNewMessageSocket(@Payload NewChatRequest messageRequest){
        Conversation conversation = this.conversationService.findConversationById(messageRequest.getConversationId());
        Chat chat = new Chat(conversation,messageRequest.getUser(), messageRequest.getMessage(), LocalDateTime.now(), LocalDateTime.now());
        this.chatService.sendChatMessage(chat);
        conversation.setUpdatedAt(LocalDateTime.now());
        this.conversationService.updateConversation(messageRequest.getConversationId(), conversation);
        this.messagingTemplate.convertAndSendToUser(
                conversation.getCustomer().getCustomerId().toString(),
                "/conversation/customer/"+messageRequest.getConversationId().toString(),
                chat
        );
        this.messagingTemplate.convertAndSendToUser(
                conversation.getCustomerSupport().getCustomerSupportId().toString(),
                "/conversation/customer_support/"+messageRequest.getConversationId().toString(),
                chat
        );
        return chat;
    }
}
