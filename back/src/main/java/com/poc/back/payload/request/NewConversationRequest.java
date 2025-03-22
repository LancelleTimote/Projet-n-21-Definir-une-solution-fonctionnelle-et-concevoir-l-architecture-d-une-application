package com.poc.back.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewConversationRequest {
    @NonNull
    private Long customerId;

    @NonNull
    private Long customerSupportId;
}
