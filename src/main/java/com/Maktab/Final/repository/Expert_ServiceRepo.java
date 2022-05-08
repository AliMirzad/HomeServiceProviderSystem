package com.Maktab.Final.repository;

import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.SubService;
import com.Maktab.Final.entity.middleEntity.Expert_Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Expert_ServiceRepo extends JpaRepository<Expert_Service, Integer> {
    public Expert_Service findExpert_ServiceByExpert(Expert expert);
    public Expert_Service findExpert_ServiceBySubService(SubService subService);
}
