package com.Maktab.Final.controller.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String nationalCode;
    @Min(8)
    private String password;
    private byte[] profileImage;
    @NotNull
    private String type;

    @Builder
    public UserDTO(Integer id, String firstName, String lastName, String email, String nationalCode, String password, byte[] profileImage, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationalCode = nationalCode;
        this.password = password;
        this.profileImage = profileImage;
        this.type = type;
    }
}
