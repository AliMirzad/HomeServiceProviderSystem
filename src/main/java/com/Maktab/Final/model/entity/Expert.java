package com.Maktab.Final.model.entity;

import com.Maktab.Final.model.entity.baseEntity.User;
import com.Maktab.Final.model.entity.enums.ExpertUserStatus;
import com.Maktab.Final.model.entity.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("experts")
public class Expert extends User {
    @Enumerated(EnumType.STRING)
    private ExpertUserStatus status;
    private Integer point;

    @Builder
    public Expert(Integer id, String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage, Role role, ExpertUserStatus status, Integer point) {
        super(id, firstName, lastName, email, nationalCode, password, registerTime, profileImage, role);
        this.status = status;
        this.point = point;
    }

    public Expert(Integer id, String firstName, String lastName, String email, String nationalCode) {
        super(id, firstName, lastName, email, nationalCode);
    }

    @Override
    public String toString() {
        return "Expert{" +
                "status=" + status +
                ", point=" + point +
                ", discriminatorValue='" + getRole() + '\'' +
                ", id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", nationalCode='" + getNationalCode() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", registerTime=" + getRegisterTime() +
                ", profileImage=" + Arrays.toString(getProfileImage()) +
                '}';
    }
}
