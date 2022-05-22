package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.ExpertDTO;
import com.Maktab.Final.controller.dto.ExpertServiceDTO;
import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.queryEntity.ServiceOrderQ;
import com.Maktab.Final.model.service.ExpertService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/")
public class ExpertController {
    private final ExpertService expertService;
    private final ModelMapper modelMapper = new ModelMapper();

    public ExpertController(ExpertService expertService) {
        this.expertService = expertService;
    }

    @GetMapping("/expert/order+list/{nationalCode}")
    public List<ServiceOrderQ> getExpertOrders(@PathVariable(name = "nationalCode") String nationalCode) {
        return expertService.findExpertServiceOrders(nationalCode);
    }

    @GetMapping("/expert/experts+service/{serviceName}")
    public List<ExpertServiceDTO> findServiceExperts(@PathVariable(name = "serviceName") String serviceName) {
        List<ExpertServiceDTO> expertServiceDTOS = new ArrayList<>();
        for (Expert e:
                expertService.findServiceExperts(serviceName)) {
            ExpertServiceDTO expertServiceDTO = new ExpertServiceDTO();
            expertServiceDTO.setId(e.getId());
            expertServiceDTO.setFirstName(e.getFirstName());
            expertServiceDTO.setLastName(e.getLastName());
            expertServiceDTO.setEmail(e.getEmail());
            expertServiceDTO.setNationalCode(e.getNationalCode());
            expertServiceDTOS.add(expertServiceDTO);
        }
        return expertServiceDTOS;
    }
}
