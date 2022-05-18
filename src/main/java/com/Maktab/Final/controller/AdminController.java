package com.Maktab.Final.controller;


import com.Maktab.Final.model.entity.Admin;
import com.Maktab.Final.model.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService1) {
        this.adminService = adminService1;
    }

    @PostMapping("/admin/create")
    public void create(@RequestBody Admin admin) {
        adminService.create(admin);
    }


//    @GetMapping("/admin/login/{nationalCode}+{password}")
//    public ResponseEntity<Admin> login(@PathVariable(name = "nationalCode") String nationalCode,
//                                @PathVariable(name = "password") String password) {
//    }
}
