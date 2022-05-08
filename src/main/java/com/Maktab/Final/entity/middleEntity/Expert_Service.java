package com.Maktab.Final.entity.middleEntity;

import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.SubService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Expert_Service")
public class Expert_Service {
    //-----------------------------------------------------fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //------------------------------------------------------relations
    @ManyToOne
    private Expert expert;
    @ManyToOne
    private SubService subService;
    //-------------------------------------------------------toString, cons
}
