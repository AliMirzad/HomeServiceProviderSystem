package com.Maktab.Final.service.serviceInterface;

import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.SubService;
import com.Maktab.Final.entity.middleEntity.Expert_Service;

public interface Expert_ServiceServInt {
    public Expert_Service findExpert_ServiceByExpert(Expert expert);
    public Expert_Service findExpert_ServiceBySubService(SubService subService);
}
