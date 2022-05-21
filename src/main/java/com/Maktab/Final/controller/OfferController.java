package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.OfferDTO;
import com.Maktab.Final.model.entity.Offer;
import com.Maktab.Final.model.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class OfferController {
    private final OfferService offerService;
    private final ModelMapper modelMapper = new ModelMapper();

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/offer/create")
    public void create(@Valid @RequestBody OfferDTO offerDTO) {
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        offerService.create(offer, offerDTO.getNationalCode(), offerDTO.getOrderId());
    }
}
