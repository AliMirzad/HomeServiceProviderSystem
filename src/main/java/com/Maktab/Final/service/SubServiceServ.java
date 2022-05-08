package com.Maktab.Final.service;

import com.Maktab.Final.entity.Services;
import com.Maktab.Final.entity.SubService;
import com.Maktab.Final.repository.SubServiceRepo;
import com.Maktab.Final.service.serviceInterface.SubServiceServInt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubServiceServ implements SubServiceServInt {
    //--------------------------------------------------------------needed
    private final SubServiceRepo subServiceRepo;

    public SubServiceServ(SubServiceRepo subServiceRepo) {
        this.subServiceRepo = subServiceRepo;
    }

    //--------------------------------------------------------------methods
    @Override
    public SubService findSubServiceById(Integer id) {
        return subServiceRepo.findSubServiceById(id);
    }

    @Override
    public List<SubService> findSubServiceByServices(Services services) {
        return subServiceRepo.findSubServiceByServices(services);
    }

    @Transactional
    public void save(SubService subService) {
        subServiceRepo.save(subService);
    }
}
