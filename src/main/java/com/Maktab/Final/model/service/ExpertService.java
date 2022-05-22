package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.*;
import com.Maktab.Final.model.entity.enums.ExpertUserStatus;
import com.Maktab.Final.model.entity.queryEntity.ServiceOrder;
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

    //---------------------------------------------------------methods
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
    public List<ServiceOrder> findExpertServiceOrders(String nationalCode) {
        List<ServiceOrder> serviceOrderList = expertRepository.findExpertServiceOrders(nationalCode);
        if (serviceOrderList == null) throw new LogicErrorException("order list is empty");
        return serviceOrderList;
    }

    public void create(Expert expert) {
        expert.setId(null);
        expert.setRegisterTime(LocalDateTime.now());
        expert.setPoint(100);
        expert.setStatus(ExpertUserStatus.waiting);
        expertRepository.save(expert);
    }
}
