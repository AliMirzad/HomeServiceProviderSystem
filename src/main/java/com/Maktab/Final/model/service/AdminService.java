package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.Admin;
import com.Maktab.Final.model.exception.LogicErrorException;
import com.Maktab.Final.model.repository.AdminRepository;
import com.Maktab.Final.model.service.serviceInterface.AdminServiceInterface;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class AdminService implements AdminServiceInterface {
    //---------------------------------------------------needed items
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    //--------------------------------------------------------methods
    @Override
    public Admin findAdminById(Integer id) {
        Admin admin = adminRepository.findAdminById(id);
        if (admin == null) throw new LogicErrorException("admin not found");
        return admin;
    }

    public void create(Admin admin) {
        if (admin.getId() != null) throw new LogicErrorException("admin id must be null/empty");
        if (admin.getEmail() == null || admin.getEmail().isEmpty()) throw new LogicErrorException("admin email can't be null/empty");
        admin.setRegisterTime(LocalDateTime.now());
        adminRepository.save(admin);
    }
}
