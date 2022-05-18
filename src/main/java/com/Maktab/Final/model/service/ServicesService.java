package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.Services;
import com.Maktab.Final.model.repository.ServicesRepository;
import com.Maktab.Final.model.service.serviceInterface.ServiceServiceInterface;
import com.Maktab.Final.model.exception.LogicErrorException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServicesService implements ServiceServiceInterface {
    //---------------------------------------------------------needed
    private final ServicesRepository servicesRepository;

    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    // ---------------------------------------------------------methods
    @Override
    public Services findServicesById(Integer id) {
        Services services = servicesRepository.findServicesById(id);
        if (services == null) throw new LogicErrorException("service not found");
        return services;
    }

    @Override
    public Services findServicesByName(String name) {
        Services services = servicesRepository.findServicesByName(name);
        if (services == null) throw new LogicErrorException("service not found");
        return services;
    }

    @Transactional
    public void create(Services services) {
        if (services.getName() == null || services.getName().isEmpty()) throw new LogicErrorException("name service can't be null/empty");
        if (servicesRepository.findServicesByName(services.getName()) != null) throw new LogicErrorException("duplicate name service found");
        servicesRepository.save(services);
    }
}
