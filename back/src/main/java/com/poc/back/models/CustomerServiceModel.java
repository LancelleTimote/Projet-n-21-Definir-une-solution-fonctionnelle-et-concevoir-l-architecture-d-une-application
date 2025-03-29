package com.poc.back.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "CUSTOMER_SERVICE")
public class CustomerServiceModel extends BaseEntity {

    @OneToOne
    @NonNull
    @JoinColumn(name="agencyid")
    private Agency agency;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "userid")
    private User customerservice;

    public CustomerServiceModel(Agency agency, User customerservice, LocalDateTime createdAt) {
        super(createdAt);
        this.agency = agency;
        this.customerservice = customerservice;
    }

    public CustomerServiceModel() {
        super(LocalDateTime.now());
    }
}
