package com.Maktab.Final.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ExpertDTO {
    //-----------------------------------------------fields
    private Integer id;
    private String firstName;
    private String lastName;
    @NotNull
    private String email;
    private String nationalCode;
    @Min(8)
    private String password;
    private byte[] profileImage;

    //----------------------------------------------toString, Cons
    @Builder
    public ExpertDTO(Integer id, String firstName, String lastName, String email, String nationalCode, String password, byte[] profileImage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationalCode = nationalCode;
        this.password = password;
        this.profileImage = profileImage;
    }
}
