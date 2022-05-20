package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.SubServiceDTO;
import com.Maktab.Final.model.entity.SubService;
import com.Maktab.Final.model.service.SubServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SubServiceController {
    private final SubServiceService subServiceService;
    private final ModelMapper modelMapper = new ModelMapper();

    public SubServiceController(SubServiceService subServiceService) {
        this.subServiceService = subServiceService;
    }

    @PostMapping("/sub+service/create")
    public void create(@Valid @RequestBody SubServiceDTO subServiceDTO) {
        SubService subService = modelMapper.map(subServiceDTO, SubService.class);
        subServiceService.create(subService, subServiceDTO.getServiceName());
    }
}
