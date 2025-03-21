package com.poc.back.repository;

import com.poc.back.model.Chat;
import com.poc.back.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByConversation(Conversation conversation);
}
