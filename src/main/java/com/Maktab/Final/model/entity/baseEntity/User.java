package com.Maktab.Final.model.entity.baseEntity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String nationalCode;
    private String password;
    private LocalDateTime registerTime;
    private byte[] profileImage;

    public User(Integer id, String firstName, String lastName, String email, String nationalCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", password='" + password + '\'' +
                ", registerTime=" + registerTime +
                ", profileImage=" + Arrays.toString(profileImage) +
                '}';
    }

    @Transient
    public String getDiscriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }


}
