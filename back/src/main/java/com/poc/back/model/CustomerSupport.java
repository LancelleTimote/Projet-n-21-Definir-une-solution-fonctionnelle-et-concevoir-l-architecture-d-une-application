package com.poc.back.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER_SUPPORT")
public class CustomerSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_support_id")
    private Long customerSupportId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User customerSupport;

    @ManyToOne
    @JoinColumn(name = "agency_id", nullable = false)
    private Agency agency;
}
