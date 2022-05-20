package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.ServiceDTO;
import com.Maktab.Final.model.service.ServicesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ServiceController {
    private final ServicesService servicesService;

    public ServiceController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }
}
