package com.poc.back.services;

import com.poc.back.models.Chat;
import com.poc.back.models.Conversation;
import com.poc.back.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> findAllByConversation(Conversation conversation){
        return this.chatRepository.findByConversation(conversation);
    }

    public Chat sendChatMessage(Chat chat){
        return this.chatRepository.save(chat);
    }

}
