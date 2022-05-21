package com.Maktab.Final.controller;

import com.Maktab.Final.model.service.ServiceExpertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class ServiceExpertController {
    private final ServiceExpertService serviceExpertService;

    public ServiceExpertController(ServiceExpertService serviceExpertService) {
        this.serviceExpertService = serviceExpertService;
    }

    @GetMapping("/service+expert/add/{nationalCode}+{subServiceName}")
    public void chooseServiceForExpert(@Valid @PathVariable(name = "nationalCode") String nationalCode, @PathVariable(name = "subServiceName") String subServiceName) {
        serviceExpertService.create(nationalCode, subServiceName);
    }
}
