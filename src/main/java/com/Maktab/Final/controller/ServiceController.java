package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.ServiceDTO;
import com.Maktab.Final.model.entity.Services;
import com.Maktab.Final.model.service.ServicesService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ServiceController {
    private final ServicesService servicesService;
    private final ModelMapper modelMapper = new ModelMapper();

    public ServiceController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @PostMapping("/services/create")
    public void create(@Valid @RequestBody ServiceDTO serviceDTO) {
        Services services = modelMapper.map(serviceDTO, Services.class);
        servicesService.create(services);
    }
}
