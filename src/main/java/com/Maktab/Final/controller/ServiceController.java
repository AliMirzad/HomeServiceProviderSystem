package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.ServiceDTO;
import com.Maktab.Final.model.entity.Services;
import com.Maktab.Final.model.service.ServicesService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ServiceController {
    private final ServicesService servicesService;
    private final ModelMapper modelMapper = new ModelMapper();

    public ServiceController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping("/services/get")
    public List<ServiceDTO> findAll() {
        List<ServiceDTO> serviceDTOS = new ArrayList<>();
        for (Services s:
                servicesService.findAll()) {
            serviceDTOS.add(modelMapper.map(s, ServiceDTO.class));
        }
        return serviceDTOS;
    }

    @PostMapping("/services/create")
    public void create(@Valid @RequestBody ServiceDTO serviceDTO) {
        Services services = modelMapper.map(serviceDTO, Services.class);
        servicesService.create(services);
    }
}
