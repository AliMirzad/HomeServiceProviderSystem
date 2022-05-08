package com.Maktab.Final.repository;

import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.Order;
import com.Maktab.Final.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepo extends JpaRepository<Services, Integer> {
    public Services findServicesById(Integer id);
}
