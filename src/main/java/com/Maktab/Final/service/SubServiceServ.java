package com.Maktab.Final.service;

import com.Maktab.Final.entity.Services;
import com.Maktab.Final.entity.SubService;
import com.Maktab.Final.repository.SubServiceRepo;
import com.Maktab.Final.service.serviceInterface.SubServiceServInt;
import exception.LogicException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubServiceServ implements SubServiceServInt {
    //--------------------------------------------------------------needed
    private final SubServiceRepo subServiceRepo;
    private final ServicesServ servicesServ;

    public SubServiceServ(SubServiceRepo subServiceRepo,@Lazy ServicesServ servicesServ) {
        this.subServiceRepo = subServiceRepo;
        this.servicesServ = servicesServ;
    }

    //--------------------------------------------------------------methods
    @Override
    public SubService findSubServiceById(Integer id) {
        try {
            SubService subService = subServiceRepo.findSubServiceById(id);
            if (subService == null) throw new LogicException("sub service is null");
            return subService;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public List<SubService> findSubServiceByServices(Services services) {
        try {
            Services services1 = servicesServ.findServicesById(services.getId());
            if (services1 == null) throw new LogicException("we need valid service");
            List<SubService> subServices = subServiceRepo.findSubServiceByServices(services);
            if (subServices == null) throw new LogicException("list is empty");
            return subServices;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Transactional
    public void save(SubService subService) {
        try {
            if (subService.getServices() == null) throw new LogicException("we need service for sub service saving");
            if (subService.getBasePrice() == null || subService.getBasePrice() <= 100)
                throw new LogicException("you need more then 100 coin for sub service");
            if (subService.getDescription() == null) throw new LogicException("we need description for sub service");
            if (subService.getName() == null) throw new LogicException("we need name for sub service");
            subServiceRepo.save(subService);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }
}
