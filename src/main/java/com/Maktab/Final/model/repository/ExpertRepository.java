package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.queryEntity.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ExpertRepository extends JpaRepository<Expert, Integer> {
    Expert findExpertById(Integer id);

    Expert findExpertByNationalCode(String nationalCode);

    @Query("select new com.Maktab.Final.model.entity.queryEntity.ServiceOrder(ss.name, o.id, o.address, o.customer.nationalCode, o.customer.lastName)  from User u join ServiceExpert se on u.id = se.expert.id join SubService ss on se.subService.id = ss.id join Order o on ss.id = o.subService.id where u.nationalCode = :nationalCode")
    public List<ServiceOrder> findExpertServiceOrders(@PathVariable(name = "nationalCode") String nationalCode);
}
