package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Services;

import java.util.List;

public interface ServiceServiceInterface {
    Services findServicesById(Integer id);

    Services findServicesDTOByName(String name);

    Services findServicesByName(String name);

    List<Services> findAll();
}
