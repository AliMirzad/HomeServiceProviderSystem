package com.Maktab.Final.service;

import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.SubService;
import com.Maktab.Final.entity.middleEntity.Expert_Service;
import com.Maktab.Final.repository.Expert_ServiceRepo;
import com.Maktab.Final.service.serviceInterface.Expert_ServiceServInt;
import exception.LogicException;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class Expert_ServiceServ implements Expert_ServiceServInt {
    //--------------------------------------------------------------needed
    private final Expert_ServiceRepo expert_ServiceRepo;
    private final ExpertServ expertServ;
    private final SubServiceServ subServiceServ;

    public Expert_ServiceServ(Expert_ServiceRepo expert_ServiceRepo, @Lazy ExpertServ expertServ,@Lazy SubServiceServ subServiceServ) {
        this.expert_ServiceRepo = expert_ServiceRepo;
        this.expertServ = expertServ;
        this.subServiceServ = subServiceServ;
    }

    //--------------------------------------------------------------methods
    @Override
    public Expert_Service findExpert_ServiceByExpert(Expert expert) {
        try {
            Expert expert1 = expertServ.findExpertById(expert.getId());
            if (expert1 == null) throw new LogicException("we dont have this expert");
            Expert_Service expert_service = expert_ServiceRepo.findExpert_ServiceByExpert(expert);
            if (expert_service == null) throw new LogicException("we dont have this entity");
            return expert_service;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public Expert_Service findExpert_ServiceBySubService(SubService subService) {
        try {
            SubService subService1 = subServiceServ.findSubServiceById(subService.getId());
            if (subService1 == null) throw new LogicException("we dont have this sub service");
            Expert_Service expert_service = expert_ServiceRepo.findExpert_ServiceBySubService(subService);
            if (expert_service == null) throw new LogicException("we dont have this entity");
            return expert_service;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Transactional
    public void save(Expert_Service expert_service) {
        try {
            Expert expert = expertServ.findExpertById(expert_service.getExpert().getId());
            if (expert == null) throw new LogicException("we dont have this expert");
            SubService subService = subServiceServ.findSubServiceById(expert_service.getSubService().getId());
            if (subService == null) throw new LogicException("we dont have this sub service");
            expert_ServiceRepo.save(expert_service);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }
}
