package com.Maktab.Final.model.entity;

import com.Maktab.Final.model.entity.baseEntity.User;
import com.Maktab.Final.model.entity.enums.ExpertUserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("expert")
public class Expert extends User {
    //---------------------------------------------------fields
    @Enumerated(EnumType.STRING)
    private ExpertUserStatus status;
    private Integer point;

    //---------------------------------------------------relations
    //----------------------------------------------toString, cons
    @Builder
    public Expert(Integer id, String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage, ExpertUserStatus status, Integer point) {
        super(id, firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.status = status;
        this.point = point;
    }

    public Expert(Integer id, String firstName, String lastName, String email, String nationalCode) {
        super(id, firstName, lastName, email, nationalCode);
    }
}
