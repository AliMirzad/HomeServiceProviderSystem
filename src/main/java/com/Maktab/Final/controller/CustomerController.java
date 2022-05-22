package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.CustomerDTO;
import com.Maktab.Final.controller.dto.ExpertDTO;
import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CustomerController {
    private final CustomerService customerService;
    private final ModelMapper modelMapper = new ModelMapper();

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/choose+offer/{offerId}+{orderId}")
    public void chooseOfferForOrder(@PathVariable(name = "offerId") Integer offerId,
                                    @PathVariable(name = "orderId") Integer orderId) {
        customerService.chooseOfferForOrder(offerId, orderId);
    }

}

