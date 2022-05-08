package com.Maktab.Final.service;

import com.Maktab.Final.entity.Services;
import com.Maktab.Final.repository.ServicesRepo;
import com.Maktab.Final.service.serviceInterface.ServiceServInt;
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
        return servicesRepo.findServicesById(id);
    }

    @Transactional
    public void save(Services services) {
        servicesRepo.save(services);
    }
}
