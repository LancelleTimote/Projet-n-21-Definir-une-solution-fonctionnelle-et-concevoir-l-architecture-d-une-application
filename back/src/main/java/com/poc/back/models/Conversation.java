package com.poc.back.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "CONVERSATIONS")
public class Conversation extends BaseEntity {

    @ManyToOne
    @NonNull
    @JoinColumn(name="customerid")
    private Customer customer;

    @ManyToOne
    @NonNull
    @JoinColumn(name="customerserviceid")
    private CustomerServiceModel customerServiceModel;

    public Conversation(Customer customer, CustomerServiceModel customerServiceModel, LocalDateTime createdAt) {
        super(createdAt);
        this.customer = customer;
        this.customerServiceModel = customerServiceModel;
    }

    public Conversation() {
        super(LocalDateTime.now());
    }
}
