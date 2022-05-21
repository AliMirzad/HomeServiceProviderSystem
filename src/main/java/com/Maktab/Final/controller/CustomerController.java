package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.CustomerDTO;
import com.Maktab.Final.controller.dto.ExpertDTO;
import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    private final CustomerService customerService;
    private final ModelMapper modelMapper = new ModelMapper();

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

}
