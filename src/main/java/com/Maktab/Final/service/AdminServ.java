package com.Maktab.Final.service;

import com.Maktab.Final.entity.Admin;
import com.Maktab.Final.entity.Services;
import com.Maktab.Final.entity.SubService;
import com.Maktab.Final.repository.AdminRepo;
import com.Maktab.Final.service.serviceInterface.AdminServInt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        return adminRepo.findAdminById(id);
    }

    @Override
    public Admin findAdminByNationalCodeAndPassword(String nationalCode, String password) {
        return adminRepo.findAdminByNationalCodeAndPassword(nationalCode, password);
    }

    @Transactional
    public void createAdmin(Admin admin) {
        adminRepo.save(admin);
    }

    @Transactional
    public void updatePassword(Integer id, String newPassword) {
        Admin admin = adminRepo.findAdminById(id);
        admin.setPassword(newPassword);
        adminRepo.save(admin);
    }

    @Transactional
    public void createService(Services services) {
        servicesServ.save(services);
    }

    @Transactional
    public void createSubService(SubService subService, Integer serviceId){
        Services services = servicesServ.findServicesById(serviceId);
        subService.setServices(services);
        subServiceServ.save(subService);
    }
}
