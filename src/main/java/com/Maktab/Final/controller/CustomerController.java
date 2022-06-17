package com.Maktab.Final.controller;

import com.Maktab.Final.config.SecurityUtil;
import com.Maktab.Final.controller.dto.PaymentDTO;
import com.Maktab.Final.model.service.CustomerService;
import org.modelmapper.ModelMapper;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin
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

    @GetMapping("/customer/walletPay/{offerId}")
    public void payForJobWallet(@PathVariable(name = "offerId") Integer offerId) {
        customerService.payForJobWallet(offerId);
    }

    @PostMapping("/customer/online+pay/")
    public void payForJobOnline(@ModelAttribute @RequestBody PaymentDTO paymentDTO) {
        customerService.payForJobOnline(paymentDTO.getOfferId());
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/test")
    public String test() {
        return SecurityUtil.getCurrentUser().toString();
    }
}

