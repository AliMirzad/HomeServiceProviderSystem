package com.Maktab.Final.model.service;

import com.Maktab.Final.controller.dto.SubServiceDTO;
import com.Maktab.Final.model.entity.Services;
import com.Maktab.Final.model.entity.SubService;
import com.Maktab.Final.model.repository.SubServiceRepository;
import com.Maktab.Final.model.service.serviceInterface.SubServiceServiceInterface;
import com.Maktab.Final.model.exception.LogicErrorException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SubServiceService implements SubServiceServiceInterface {
    //--------------------------------------------------------------needed
    private final SubServiceRepository subServiceRepository;
    private final ServicesService servicesService;

    public SubServiceService(SubServiceRepository subServiceRepository, @Lazy ServicesService servicesService) {
        this.subServiceRepository = subServiceRepository;
        this.servicesService = servicesService;
    }

    //--------------------------------------------------------------methods
    @Override
    public SubService findSubServiceById(Integer id) {
        SubService subService = subServiceRepository.findSubServiceById(id);
        if (subService == null) throw new LogicErrorException("sub service not found");
        return subService;
    }

    @Override
    public SubService findSubServiceByName(String name) {
        SubService subService = subServiceRepository.findSubServiceByName(name);
        if (subService == null) throw new LogicErrorException("sub service not found");
        return subService;
    }

    @Override
    public List<SubService> findAll() {
        List<SubService> subServices = subServiceRepository.findAll();
        if (subServices == null) throw new LogicErrorException("service list is empty");
        subServices.forEach(System.out::println);
        return subServices;
    }

    @Override
    public List<SubService> findSubServiceByServices(Services services) {
        if (servicesService.findServicesById(services.getId()) == null) throw new LogicErrorException("service sub service not found");
        List<SubService> subServices = subServiceRepository.findSubServiceByServices(services);
        if (subServices == null) throw new LogicErrorException("sub service list is empty");
        return subServices;
    }


    public void create(SubService subService, String serviceName) {
        Services services = servicesService.findServicesByName(serviceName);
        if (services == null) throw new LogicErrorException("service sub service not found");
        subService.setServices(services);
        if (subService.getBasePrice() == null || subService.getBasePrice() <= 100)
            throw new LogicErrorException("base price sub service must be more then 100 coin");
        if (subService.getDescription() == null || subService.getDescription().isEmpty()) throw new LogicErrorException("description sub service can't be null/empty");
        if (subService.getName() == null || subService.getName().isEmpty()) throw new LogicErrorException("name sub service can't be null/empty");
        subServiceRepository.save(subService);
    }

}
