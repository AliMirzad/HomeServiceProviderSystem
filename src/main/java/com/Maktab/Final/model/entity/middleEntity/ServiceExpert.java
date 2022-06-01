package com.Maktab.Final.model.entity.middleEntity;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.SubService;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Services_Experts")
public class ServiceExpert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Expert expert;
    @ManyToOne
    private SubService subService;

    @Builder
    public ServiceExpert(Integer id, Expert expert, SubService subService) {
        this.id = id;
        this.expert = expert;
        this.subService = subService;
    }

    @Override
    public String toString() {
        return "ServiceExpert{" +
                "id=" + id +
                ", expert=" + expert +
                ", subService=" + subService +
                '}';
    }
}
