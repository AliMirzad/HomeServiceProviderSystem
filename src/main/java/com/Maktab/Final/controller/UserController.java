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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
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
    public void register(@RequestBody @Valid UserDTO userDTO) {
        if (userDTO.getType().equals("expert")) {
            if (userDTO.getProfileImage() == null) throw new LogicErrorException("expert user need image");
            Expert expert = modelMapper.map(userDTO, Expert.class);
            expertService.create(expert);
        }
        else if (userDTO.getType().equals("customer")) {
            Customer customer = modelMapper.map(userDTO, Customer.class);
            customerService.create(customer);
        }
    }
}
