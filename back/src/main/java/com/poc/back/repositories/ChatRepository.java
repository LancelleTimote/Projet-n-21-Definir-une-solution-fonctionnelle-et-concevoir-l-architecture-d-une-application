package com.poc.back.repositories;

import com.poc.back.models.Chat;
import com.poc.back.models.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByConversation(Conversation conversation);
}
