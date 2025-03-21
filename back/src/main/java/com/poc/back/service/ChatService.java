package com.poc.back.service;

import com.poc.back.model.Chat;
import com.poc.back.model.Conversation;
import com.poc.back.repository.ChatRepository;
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
