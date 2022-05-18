package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.SubService;
import com.Maktab.Final.model.entity.middleEntity.ServiceExpert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceExpertRepository extends JpaRepository<ServiceExpert, Integer> {
    public ServiceExpert findServiceExpertByExpert(Expert expert);
    public ServiceExpert findServiceExpertBySubService(SubService subService);
}
