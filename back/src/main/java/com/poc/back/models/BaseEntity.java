package com.poc.back.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @NonNull
    @UpdateTimestamp
    @Column(name = "updatedat")
    private LocalDateTime updatedAt;

    public BaseEntity(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public BaseEntity() {
        this.createdAt = LocalDateTime.now();
    }
}
