package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.*;
import com.Maktab.Final.model.entity.enums.ExpertUserStatus;
import com.Maktab.Final.model.entity.queryEntity.ServiceOrderQ;
import com.Maktab.Final.model.repository.ExpertRepository;
import com.Maktab.Final.model.service.serviceInterface.ExpertServiceInterface;
import com.Maktab.Final.model.exception.LogicErrorException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ExpertService implements ExpertServiceInterface {
    //---------------------------------------------------------needed
    private final ExpertRepository expertRepository;

    public ExpertService(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }

    @Override
    public Expert findExpertById(Integer id) {
        Expert expert = expertRepository.findExpertById(id);
        if (expert == null) throw new LogicErrorException("expert not found");
        return expert;
    }

    @Override
    public Expert findExpertByNationalCode(String nationalCode) {
        Expert expert = expertRepository.findExpertByNationalCode(nationalCode);
        if (expert == null) throw new LogicErrorException("expert not found");
        return expert;
    }

    @Override
    public List<ServiceOrderQ> findExpertServiceOrders(String nationalCode) {
        List<ServiceOrderQ> serviceOrderQList = expertRepository.findExpertServiceOrders(nationalCode);
        if (serviceOrderQList == null) throw new LogicErrorException("order list is empty");
        return serviceOrderQList;
    }

    @Override
    public List<Expert> findServiceExperts(String serviceName) {
        List<Expert> experts = expertRepository.findServiceExperts(serviceName);
        if (experts == null) throw new LogicErrorException("expert list is empty");
        return experts;
    }

    public void create(Expert expert) {
        expert.setId(null);
        expert.setRegisterTime(LocalDateTime.now());
        expert.setPoint(100);
        expert.setStatus(ExpertUserStatus.waiting);
        expertRepository.save(expert);
    }
}
