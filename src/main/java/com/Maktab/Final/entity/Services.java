package com.Maktab.Final.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "services")
public class Services {
    //--------------------------------------------------------fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    //----------------------------------------------------relations
    //------------------------------------------------------toString, cons
    @Builder
    public Services(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
