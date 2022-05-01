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
    private String name;
    private Double basePrice;
    private String description;
    @Enumerated(EnumType.STRING)
    private ServiceModel model;
    @OneToOne
    private Service parentService;

    @Builder
    public Service(Integer id, String name, Double basePrice, String description, ServiceModel model, Service parentService) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
        this.model = model;
        this.parentService = parentService;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", name='" + name + ", basePrice=" + basePrice + ", description='" + description + ", model=" + model + ", parentService=" + parentService + '}';
    }
}
