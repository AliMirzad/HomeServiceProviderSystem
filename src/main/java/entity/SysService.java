package entity;

import entity.enums.ServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
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
}
