package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Integer name;
    private Double baseSalary;
    private String description;
    @OneToMany
    @JoinTable(
            name = "service_subService"
    )
    private Set<Service> subServices;

    @Builder
    public Service(Integer id, Integer name, Double baseSalary, String description, Set<Service> subServices) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
        this.description = description;
        this.subServices = subServices;
    }
}
