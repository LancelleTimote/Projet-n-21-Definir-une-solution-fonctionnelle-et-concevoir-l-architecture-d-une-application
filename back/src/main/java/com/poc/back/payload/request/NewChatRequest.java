package com.poc.back.payload.request;

import com.poc.back.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewChatRequest {
    private Long conversationId;
    private User user;
    private String message;
}
