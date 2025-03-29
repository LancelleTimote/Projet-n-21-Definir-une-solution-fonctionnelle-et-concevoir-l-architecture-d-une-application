package com.poc.back.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="AGENCIES")
public class Agency extends BaseEntity {

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "address")
    private String address;

    public Agency(String name, String address, LocalDateTime createdAt) {
        super(createdAt);
        this.name = name;
        this.address = address;
    }

    public Agency() {
        super(LocalDateTime.now());
    }
}
