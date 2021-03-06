package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.queryEntity.ServiceOrderQ;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ExpertServiceInterface {
    Expert findExpertById(Integer id);

    Expert findExpertByNationalCode(String nationalCode);

    List<ServiceOrderQ> findExpertServiceOrders(String nationalCode);

    List<Expert> findServiceExperts(String serviceName);
}
