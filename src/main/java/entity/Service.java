package entity;

import entity.enums.ServiceModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Integer name;
    private Double basePrice;
    private String description;
    private ServiceModel model;
    @OneToOne
    private Service parentService;

    @Builder
    public Service(Integer id, Integer name, Double basePrice, String description, ServiceModel model, Service parentService) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
        this.model = model;
        this.parentService = parentService;
    }
}
