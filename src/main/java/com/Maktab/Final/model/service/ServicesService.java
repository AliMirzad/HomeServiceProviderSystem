package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.Services;
import com.Maktab.Final.model.repository.ServicesRepository;
import com.Maktab.Final.model.service.serviceInterface.ServiceServiceInterface;
import com.Maktab.Final.model.exception.LogicErrorException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicesService implements ServiceServiceInterface {
    private final ServicesRepository servicesRepository;

    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    @Override
    public Services findServicesById(Integer id) {
        Services services = servicesRepository.findServicesById(id);
        if (services == null) throw new LogicErrorException("service not found");
        return services;
    }

    @Override
    public List<Services> findAll() {
        List<Services> services = servicesRepository.findAll();
        if (services == null) throw new LogicErrorException("service list is empty");
        return services;
    }

    @Override
    public Services findServicesDTOByName(String name) {
        Services services = servicesRepository.findServicesByName(name);
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
    public void create(Services service) {
        if (servicesRepository.findServicesByName(service.getName()) != null) throw new LogicErrorException("duplicate name service found");
        servicesRepository.save(service);
    }
}
