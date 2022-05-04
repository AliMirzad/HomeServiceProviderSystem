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
public class SysService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    private Double basePrice;
    private String description;
    @Enumerated(EnumType.STRING)
    private ServiceModel model;


    @OneToOne
    private SysService parentService;
    @OneToMany
    private Set<Order> orders;

    @Builder
    public SysService(Integer id, String name, Double basePrice, String description, ServiceModel model, SysService parentService) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
        this.model = model;
        this.parentService = parentService;
    }
}
