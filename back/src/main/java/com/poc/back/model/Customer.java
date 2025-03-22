package com.poc.back.model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User customer;

    @Column(name = "address", nullable = false)
    private String address;
}
