package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.controller.dto.SubServiceDTO;
import com.Maktab.Final.model.entity.Services;
import com.Maktab.Final.model.entity.SubService;

import java.util.List;

public interface SubServiceServiceInterface {
    SubService findSubServiceById(Integer id);

    List<SubService> findSubServiceByServices(Services services);

    List<SubService> findAll();
}
