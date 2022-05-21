package com.Maktab.Final.controller;


import com.Maktab.Final.controller.dto.AdminDTO;
import com.Maktab.Final.model.entity.Admin;
import com.Maktab.Final.model.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class AdminController {
    private final AdminService adminService;
    private final ModelMapper modelMapper = new ModelMapper();

    public AdminController(AdminService adminService1) {
        this.adminService = adminService1;
    }
}
