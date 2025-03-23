package com.poc.back.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;


@Entity
@Data
@Table(name = "CUSTOMER_SERVICE")
public class CustomerServiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerserviceid;

    @OneToOne
    @NonNull
    @JoinColumn(name="agencyid")
    private Agency agency;


    @ManyToOne
    @NonNull
    @JoinColumn(name = "userid")
    private User customerservice;

    public CustomerServiceModel(){}

    public CustomerServiceModel(Agency agency, User customerservice){
        this.agency=agency;
        this.customerservice=customerservice;
    }
}
