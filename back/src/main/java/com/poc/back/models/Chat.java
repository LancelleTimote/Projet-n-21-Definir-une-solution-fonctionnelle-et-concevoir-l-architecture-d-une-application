package com.poc.back.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="CHAT")
public class Chat extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="conversationid")
    private Conversation conversation;

    @OneToOne
    @JoinColumn(name="messagesenderid")
    private User user;

    @Column(name="message")
    private String message;

    public Chat(Conversation conversation, User user, String message, LocalDateTime createdAt) {
        super(createdAt);
        this.conversation = conversation;
        this.user = user;
        this.message = message;
    }

    public Chat() {
        super(LocalDateTime.now());
    }
}
