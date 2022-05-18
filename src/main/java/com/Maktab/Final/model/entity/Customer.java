package com.Maktab.Final.model.entity;

import com.Maktab.Final.model.entity.baseEntity.User;
import com.Maktab.Final.model.entity.enums.CustomerUserStatus;
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
@DiscriminatorValue("customer")
public class Customer extends User {
    //-----------------------------------------------fields
    @Enumerated(EnumType.STRING)
    private CustomerUserStatus status;

    //-----------------------------------------------relation
    //----------------------------------------------toString, Cons
    @Builder
    public Customer(Integer id, String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage, CustomerUserStatus status) {
        super(id, firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "status=" + status +
                '}';
    }
}
