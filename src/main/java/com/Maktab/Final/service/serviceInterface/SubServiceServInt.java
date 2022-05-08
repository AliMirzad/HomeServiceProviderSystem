package com.Maktab.Final.service.serviceInterface;

import com.Maktab.Final.entity.Services;
import com.Maktab.Final.entity.SubService;

import java.util.List;

public interface SubServiceServInt {
    public SubService findSubServiceById(Integer id);
    public List<SubService> findSubServiceByServices(Services services);
}
