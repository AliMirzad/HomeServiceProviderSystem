package com.Maktab.Final.entity;

import com.Maktab.Final.entity.baseEntity.User;
import com.Maktab.Final.entity.enums.ExpertUserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("expert")
@Table(name = "experts")
public class Expert extends User {
    //---------------------------------------------------fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ExpertUserStatus status;
    private Integer point;
    //---------------------------------------------------relations
    //----------------------------------------------toString, cons
    @Builder
    public Expert(Integer id, String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage, Integer id1, ExpertUserStatus status, Integer point) {
        super(id, firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.id = id1;
        this.status = status;
        this.point = point;
    }
}
