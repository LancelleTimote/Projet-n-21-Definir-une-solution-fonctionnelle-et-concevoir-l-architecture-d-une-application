package com.poc.back.payload.request;

import com.poc.back.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewChatRequest {
    @NonNull
    private Long conversationid;

    @NonNull
    private User user;

    @NonNull
    private String message;
}
