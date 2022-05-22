package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.WalletDTO;
import com.Maktab.Final.model.entity.Wallet;
import com.Maktab.Final.model.service.WalletService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class WalletController {
    private final WalletService walletService;
    private final ModelMapper modelMapper = new ModelMapper();

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/wallet/customer+wallet/{nationalCode}")
    public WalletDTO findWalletCustomer(@PathVariable(name = "nationalCode") String nationalCode) {
        WalletDTO walletDTO = modelMapper.map(walletService.findWalletByCustomer(nationalCode), WalletDTO.class);
        walletDTO.setNationalCode(nationalCode);
        return walletDTO;
    }

    @GetMapping("/wallet/expert+wallet/{nationalCode}")
    public WalletDTO findWalletExpert(@PathVariable(name = "nationalCode") String nationalCode) {
        WalletDTO walletDTO = modelMapper.map(walletService.findWalletByExpert(nationalCode), WalletDTO.class);
        walletDTO.setNationalCode(nationalCode);
        return walletDTO;
    }
}
