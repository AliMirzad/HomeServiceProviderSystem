package com.Maktab.Final.entity;

import com.Maktab.Final.entity.enums.ServiceModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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
