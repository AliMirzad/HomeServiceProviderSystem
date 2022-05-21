package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Integer> {
    Services findServicesById(Integer id);

    Services findServicesByName(String name);

    List<Services> findAll();
}
