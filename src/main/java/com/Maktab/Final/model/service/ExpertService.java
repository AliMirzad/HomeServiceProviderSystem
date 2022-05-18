package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.*;
import com.Maktab.Final.model.entity.enums.ExpertUserStatus;
import com.Maktab.Final.model.repository.ExpertRepository;
import com.Maktab.Final.model.service.serviceInterface.ExpertServiceInterface;
import com.Maktab.Final.model.exception.LogicErrorException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

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
        if (expert == null) throw new LogicErrorException("we need balance for wallet");
        return expert;
    }


    public void create(Expert expert) {
        if (expert.getId() != null) throw new LogicErrorException("expert id must be null/empty");
        if (expert.getEmail() == null || expert.getEmail().isEmpty()) throw new LogicErrorException("expert email can't be null/empty");
        expert.setRegisterTime(LocalDateTime.now());
        expert.setPoint(100);
        expert.setStatus(ExpertUserStatus.waiting);
        expertRepository.save(expert);
    }
}