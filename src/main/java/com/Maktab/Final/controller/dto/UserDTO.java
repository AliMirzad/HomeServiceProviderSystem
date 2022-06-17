package com.Maktab.Final.controller.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;

    private String email;

    private String nationalCode;

    private String password;
    private MultipartFile profileImage;

    private String type;

    @Builder
    public UserDTO(Integer id, String firstName, String lastName, String email, String nationalCode, String password, MultipartFile profileImage, String type) {
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
