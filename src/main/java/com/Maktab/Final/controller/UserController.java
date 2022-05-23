package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.UserDTO;
import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.baseEntity.User;
import com.Maktab.Final.model.exception.LogicErrorException;
import com.Maktab.Final.model.service.CustomerService;
import com.Maktab.Final.model.service.ExpertService;
import com.Maktab.Final.model.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/")
public class UserController {
    private final UserService userService;
    private final ExpertService expertService;
    private final CustomerService customerService;
    private final ModelMapper modelMapper = new ModelMapper();

    public UserController(UserService userService, ExpertService expertService, CustomerService customerService) {
        this.userService = userService;
        this.expertService = expertService;
        this.customerService = customerService;
    }

    @PostMapping("/user/register")
    public void register(@ModelAttribute @RequestBody @Valid UserDTO userDTO) {
        if (userDTO.getType().equals("expert")) {
            if (userDTO.getProfileImage() == null) throw new LogicErrorException("expert user need image");
//            Expert expert = modelMapper.map(userDTO, Expert.class);
            Expert expert = Expert.builder().firstName(userDTO.getFirstName()).lastName(userDTO.getLastName()).email(userDTO.getEmail()).nationalCode(userDTO.getNationalCode()).password(userDTO.getPassword()).build();
            try {
                expert.setProfileImage(userDTO.getProfileImage().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            expertService.create(expert);
        }
        else if (userDTO.getType().equals("customer")) {
            Customer customer = modelMapper.map(userDTO, Customer.class);
            customerService.create(customer);
        }
    }
}
