package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<Services, Integer> {
    public Services findServicesById(Integer id);
    public Services findServicesByName(String name);
}
