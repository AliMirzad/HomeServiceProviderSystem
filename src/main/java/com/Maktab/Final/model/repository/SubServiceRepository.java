package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Services;
import com.Maktab.Final.model.entity.SubService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubServiceRepository extends JpaRepository<SubService, Integer> {
    SubService findSubServiceById(Integer id);

    List<SubService> findSubServiceByServices(Services services);

    List<SubService> findAll();
}
