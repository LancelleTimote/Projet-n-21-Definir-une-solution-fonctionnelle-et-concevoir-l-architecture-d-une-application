package com.poc.back.controller;

import com.poc.back.dto.NewChatRequest;
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
    public ResponseEntity<?> getChatMessages(@PathVariable Long id) {
        log.info("Récupération des messages pour la conversation ID : {}", id);
        Conversation conversation = conversationService.findConversationById(id);
        if (conversation == null) {
            log.warn("Conversation non trouvée : {}", id);
            return ResponseEntity.notFound().build();
        }
        List<Chat> chatList = chatService.findAllByConversation(conversation);
        return ResponseEntity.ok(chatList);
    }

    @MessageMapping("/get_chat_messages/{conversationId}")
    @SendTo("/topic/getMessages/{conversationId}")
    public List<Chat> getChatMessagesSocket(@DestinationVariable Long conversationId) {
        log.info("WebSocket - Chargement des messages pour la conversation ID : {}", conversationId);
        Conversation conversation = conversationService.findConversationById(conversationId);
        return conversation != null ? chatService.findAllByConversation(conversation) : List.of();
    }

    @MessageMapping("/sendMessage/{conversationId}")
    @SendTo("/topic/message_sent/{conversationId}")
    public Chat sendChatMessageSocket(@Payload Chat messageRequest, @DestinationVariable Long conversationId) {
        log.info("WebSocket - Envoi d'un message dans la conversation ID : {}", conversationId);

        Conversation conversation = conversationService.findConversationById(conversationId);
        if (conversation == null) {
            log.error("Impossible d'envoyer le message, conversation non trouvée : {}", conversationId);
            return null;
        }

        Chat chat = new Chat(conversation, messageRequest.getUser(), messageRequest.getMessage(), LocalDateTime.now(), LocalDateTime.now());
        chatService.sendChatMessage(chat);

        conversation.setUpdatedAt(LocalDateTime.now());
        conversationService.updateConversation(conversationId, conversation);

        return chat;
    }

    @MessageMapping("/sendNewMessage")
    public void sendNewMessageSocket(@Payload NewChatRequest messageRequest) {
        log.info("WebSocket - Nouveau message reçu pour la conversation ID : {}", messageRequest.getConversationId());

        Conversation conversation = conversationService.findConversationById(messageRequest.getConversationId());
        if (conversation == null) {
            log.warn("Impossible d'envoyer le message, conversation non trouvée.");
            return;
        }

        Chat chat = new Chat(conversation, messageRequest.getUser(), messageRequest.getMessage(), LocalDateTime.now(), LocalDateTime.now());
        chatService.sendChatMessage(chat);

        conversation.setUpdatedAt(LocalDateTime.now());
        conversationService.updateConversation(messageRequest.getConversationId(), conversation);

        sendToUsers(conversation, chat);
    }

    private void sendToUsers(Conversation conversation, Chat chat) {
        log.info("Envoi du message aux utilisateurs impliqués dans la conversation ID : {}", conversation.getId());

        if (conversation.getCustomer() != null) {
            messagingTemplate.convertAndSendToUser(
                    conversation.getCustomer().getCustomerId().toString(),
                    "/conversation/customer/" + conversation.getId(),
                    chat
            );
        }

        if (conversation.getCustomerSupport() != null) {
            messagingTemplate.convertAndSendToUser(
                    conversation.getCustomerSupport().getCustomerSupportId().toString(),
                    "/conversation/customer_support/" + conversation.getId(),
                    chat
            );
        }
    }
}
