package com.poc.back.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="CUSTOMERS")
public class Customer extends BaseEntity {

    @NonNull
    @Column(name = "address")
    private String address;

    @ManyToOne
    @NonNull
    @JoinColumn(name="userid")
    private User customer;

    public Customer(String address, User customer, LocalDateTime createdAt) {
        super(createdAt);
        this.address = address;
        this.customer = customer;
    }

    public Customer() {
        super(LocalDateTime.now());
    }
}
