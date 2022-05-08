package com.Maktab.Final.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sub_services")
public class SubService {
    //--------------------------------------------------------fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double basePrice;
    private String description;
    //----------------------------------------------------relations
    @ManyToOne
    private Services services;
    //------------------------------------------------------toString, cons

    public SubService(Integer id, String name, Double basePrice, String description, Services services) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
        this.services = services;
    }
}