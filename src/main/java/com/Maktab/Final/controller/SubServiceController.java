package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.SubServiceDTO;
import com.Maktab.Final.model.service.SubServiceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubServiceController {
    private final SubServiceService subServiceService;

    public SubServiceController(SubServiceService subServiceService) {
        this.subServiceService = subServiceService;
    }
}
