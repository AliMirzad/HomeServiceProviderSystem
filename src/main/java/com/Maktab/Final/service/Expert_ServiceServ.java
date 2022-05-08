package com.Maktab.Final.service;

import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.SubService;
import com.Maktab.Final.entity.middleEntity.Expert_Service;
import com.Maktab.Final.repository.Expert_ServiceRepo;
import com.Maktab.Final.service.serviceInterface.Expert_ServiceServInt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class Expert_ServiceServ implements Expert_ServiceServInt {
    //--------------------------------------------------------------needed
    private final Expert_ServiceRepo expert_ServiceRepo;

    public Expert_ServiceServ(Expert_ServiceRepo expert_ServiceRepo) {
        this.expert_ServiceRepo = expert_ServiceRepo;
    }

    //--------------------------------------------------------------methods
    @Override
    public Expert_Service findExpert_ServiceByExpert(Expert expert) {
        return expert_ServiceRepo.findExpert_ServiceByExpert(expert);
    }

    @Override
    public Expert_Service findExpert_ServiceBySubService(SubService subService) {
        return expert_ServiceRepo.findExpert_ServiceBySubService(subService);
    }

    @Transactional
    public void save(Expert_Service expert_service) {
        expert_ServiceRepo.save(expert_service);
    }
}
