package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Services;
import com.Maktab.Final.model.entity.SubService;

import java.util.List;

public interface SubServiceServiceInterface {
    public SubService findSubServiceById(Integer id);
    public List<SubService> findSubServiceByServices(Services services);
}
