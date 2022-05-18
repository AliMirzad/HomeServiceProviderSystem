package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.SubService;
import com.Maktab.Final.model.entity.middleEntity.ServiceExpert;
import com.Maktab.Final.model.repository.ServiceExpertRepository;
import com.Maktab.Final.model.service.serviceInterface.ServiceExpertServiceInterface;
import com.Maktab.Final.model.exception.LogicErrorException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServiceExpertService implements ServiceExpertServiceInterface {
    //--------------------------------------------------------------needed
    private final ServiceExpertRepository serviceExpertRepository;
    private final ExpertService expertService;
    private final SubServiceService subServiceServ;

    public ServiceExpertService(ServiceExpertRepository ServiceExpertRepository, @Lazy ExpertService expertService, @Lazy SubServiceService subServiceServ) {
        this.serviceExpertRepository = ServiceExpertRepository;
        this.expertService = expertService;
        this.subServiceServ = subServiceServ;
    }

    //--------------------------------------------------------------methods
    @Override
    public ServiceExpert findServiceExpertByExpert(Expert expert) {
        if (expertService.findExpertById(expert.getId()) == null)
            throw new LogicErrorException("expert expert-service not found");
        ServiceExpert serviceExpert = serviceExpertRepository.findServiceExpertByExpert(expert);
        if (serviceExpert == null) throw new LogicErrorException("service-expert not found");
        return serviceExpert;
    }

    @Override
    public ServiceExpert findServiceExpertBySubService(SubService subService) {
        if (subServiceServ.findSubServiceById(subService.getId()) == null)
            throw new LogicErrorException("sub service service-expert not found");
        ServiceExpert serviceExpert = serviceExpertRepository.findServiceExpertBySubService(subService);
        if (serviceExpert == null) throw new LogicErrorException("service-expert not found");
        return serviceExpert;
    }

    public void save(ServiceExpert serviceExpert) {
        if (expertService.findExpertById(serviceExpert.getExpert().getId()) == null)
            throw new LogicErrorException("expert expert service-expert not found");
        if (subServiceServ.findSubServiceById(serviceExpert.getSubService().getId()) == null)
            throw new LogicErrorException("sub service service-expert not found");
        serviceExpertRepository.save(serviceExpert);
    }
}
