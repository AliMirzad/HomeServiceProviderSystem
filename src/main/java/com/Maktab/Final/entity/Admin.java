package com.Maktab.Final.entity;

import com.Maktab.Final.entity.baseEntity.User;
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
@DiscriminatorValue("admin")
@Table(name = "admins")
public class Admin extends User {
    @Builder
    public Admin(Integer id, String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage) {
        super(id, firstName, lastName, email, nationalCode, password, registerTime, profileImage);
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + getId() + " ,name=" + getFirstName() + " " + getLastName() + " ,nationalCode=" + getNationalCode() + " ,password=" + getPassword() + '}';
    }
}