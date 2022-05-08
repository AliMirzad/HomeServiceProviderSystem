package entity;

import entity.enums.ServiceModel;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    private Double basePrice;
    private String description;
    @Enumerated(EnumType.STRING)
    private ServiceModel model;
    //-----------------------------------------------relations
    @ManyToOne
    private Expert expert;
    @OneToOne
    private Services parentService;
    @OneToMany(mappedBy = "services")
    private Set<Order> orders;
    //------------------------------------------------------toString, cons
    @Builder
    public Services(Integer id, String name, Double basePrice, String description, ServiceModel model, Expert expert, Services parentService, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
        this.model = model;
        this.expert = expert;
        this.parentService = parentService;
        this.orders = orders;
    }
}
