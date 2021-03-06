package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.SubService;
import com.Maktab.Final.model.entity.middleEntity.ServiceExpert;

public interface ServiceExpertServiceInterface {
    ServiceExpert findServiceExpertByExpert(Expert expert);

    ServiceExpert findServiceExpertBySubService(SubService subService);
}
