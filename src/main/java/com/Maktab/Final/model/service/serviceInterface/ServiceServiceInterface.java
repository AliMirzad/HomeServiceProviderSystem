package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Services;

public interface ServiceServiceInterface {
    public Services findServicesById(Integer id);
    public Services findServicesByName(String name);
}
