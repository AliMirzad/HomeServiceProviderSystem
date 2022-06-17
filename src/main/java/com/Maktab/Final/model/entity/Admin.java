package com.Maktab.Final.model.entity;

import com.Maktab.Final.model.entity.baseEntity.User;
import com.Maktab.Final.model.entity.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("admin")
public class Admin extends User {

    @Builder
    public Admin(Integer id, String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage, Role role) {
        super(id, firstName, lastName, email, nationalCode, password, registerTime, profileImage, role);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "discriminatorValue='" + getRole() + '\'' +
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
