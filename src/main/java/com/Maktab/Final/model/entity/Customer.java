package com.Maktab.Final.model.entity;

import com.Maktab.Final.model.entity.baseEntity.User;
import com.Maktab.Final.model.entity.enums.CustomerUserStatus;
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
@DiscriminatorValue("customers")
public class Customer extends User {
    @Enumerated(EnumType.STRING)
    private CustomerUserStatus status;

    @Builder
    public Customer(Integer id, String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage, CustomerUserStatus status) {
        super(id, firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "status=" + status +
                ", discriminatorValue='" + getDiscriminatorValue() + '\'' +
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
