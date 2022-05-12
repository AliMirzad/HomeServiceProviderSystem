package com.Maktab.Final.service;

import com.Maktab.Final.entity.Services;
import com.Maktab.Final.repository.ServicesRepo;
import com.Maktab.Final.service.serviceInterface.ServiceServInt;
import exception.LogicException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ServicesServ implements ServiceServInt {
    //---------------------------------------------------------needed
    private ServicesRepo servicesRepo;

    public ServicesServ(ServicesRepo servicesRepo) {
        this.servicesRepo = servicesRepo;
    }

    // ---------------------------------------------------------methods
    @Override
    public Services findServicesById(Integer id) {
        try {
            Services services = servicesRepo.findServicesById(id);
            if (services == null) throw new LogicException("we need valid services");
            return services;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Transactional
    public void save(Services services) {
        try {
            if (services.getName() == null) throw new LogicException("we need name for services");
            servicesRepo.save(services);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }
}
