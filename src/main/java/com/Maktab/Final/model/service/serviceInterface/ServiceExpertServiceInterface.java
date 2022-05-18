package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.SubService;
import com.Maktab.Final.model.entity.middleEntity.ServiceExpert;

public interface ServiceExpertServiceInterface {
    public ServiceExpert findServiceExpertByExpert(Expert expert);
    public ServiceExpert findServiceExpertBySubService(SubService subService);
}
