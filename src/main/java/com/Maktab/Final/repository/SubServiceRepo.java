package com.Maktab.Final.repository;

import com.Maktab.Final.entity.Services;
import com.Maktab.Final.entity.SubService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubServiceRepo extends JpaRepository<SubService, Integer> {
    public SubService findSubServiceById(Integer id);
    public List<SubService> findSubServiceByServices(Services services);
}
