package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.SubServiceDTO;
import com.Maktab.Final.model.entity.SubService;
import com.Maktab.Final.model.service.SubServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SubServiceController {
    private final SubServiceService subServiceService;
    private final ModelMapper modelMapper = new ModelMapper();

    public SubServiceController(SubServiceService subServiceService) {
        this.subServiceService = subServiceService;
    }

    @GetMapping("/sub+service/get")
    public List<SubServiceDTO> findAll() {
        List<SubServiceDTO> subServiceDTOS = new ArrayList<>();
        for (SubService s:
                subServiceService.findAll()) {
            SubServiceDTO subServiceDTO = modelMapper.map(s, SubServiceDTO.class);
            subServiceDTO.setServiceName(s.getServices().getName());
            subServiceDTOS.add(subServiceDTO);
        }
        return subServiceDTOS;
    }

    @PostMapping("/sub+service/create")
    public void create(@Valid @RequestBody SubServiceDTO subServiceDTO) {
        SubService subService = modelMapper.map(subServiceDTO, SubService.class);
        subServiceService.create(subService, subServiceDTO.getServiceName());
    }
}
