package com.Maktab.Final.service;

import ch.qos.logback.core.LogbackException;
import com.Maktab.Final.entity.Admin;
import com.Maktab.Final.entity.Services;
import com.Maktab.Final.entity.SubService;
import com.Maktab.Final.repository.AdminRepo;
import com.Maktab.Final.service.serviceInterface.AdminServInt;
import exception.LogicException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class AdminServ implements AdminServInt {
    //---------------------------------------------------needed items
    private final AdminRepo adminRepo;
    private final ServicesServ servicesServ;
    private final SubServiceServ subServiceServ;

    public AdminServ(AdminRepo adminRepo, ServicesServ servicesServ, SubServiceServ subServiceServ) {
        this.adminRepo = adminRepo;
        this.servicesServ = servicesServ;
        this.subServiceServ = subServiceServ;
    }

    //--------------------------------------------------------methods
    @Override
    public Admin findAdminById(Integer id) {
        try {
            Admin admin = adminRepo.findAdminById(id);
            if (admin == null) throw new LogicException("we dont have this id");
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public Admin findAdminByNationalCodeAndPassword(String nationalCode, String password) {
        try {
            Admin admin = adminRepo.findAdminByNationalCodeAndPassword(nationalCode, password);
            if (admin == null) throw new LogicException("we dont have this id");
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Transactional
    public void createAdmin(Admin admin) {
        try {
            Admin admin1 = adminRepo.findAdminById(admin.getId());
            if (admin1 != null) throw new LogicException("we have this admin before");
            if (admin.getEmail() == null || admin.getEmail().isEmpty())
                throw new LogicException("admin must have email");
            admin.setRegisterTime(LocalDateTime.now());
            adminRepo.save(admin);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Transactional
    public void updatePassword(Integer id, String newPassword) {
        try {
            Admin admin = adminRepo.findAdminById(id);
            if (admin.getPassword().equals(newPassword)) throw new LogicException("this is your old pass");
            admin.setPassword(newPassword);
            adminRepo.save(admin);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Transactional
    public void createService(Services services) {
        servicesServ.save(services);
    }

    @Transactional
    public void createSubService(SubService subService, Integer serviceId) {
        Services services = servicesServ.findServicesById(serviceId);
        subService.setServices(services);
        subServiceServ.save(subService);
    }
}
