package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.OfferDTO;
import com.Maktab.Final.controller.dto.OrderDTO;
import com.Maktab.Final.model.entity.Offer;
import com.Maktab.Final.model.entity.Order;
import com.Maktab.Final.model.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/offer/sort/{orderId}+{sortName}+{nationalCode}")
    public List<OfferDTO> findOfferByOrderWithSort(@PathVariable(name = "orderId") Integer orderId, @PathVariable(name = "sortName") String sortName, @PathVariable(name = "nationalCode") String nationalCode) {
        List<Offer> findWithSort = offerService.findOfferByOrderWithSort(orderId, sortName, nationalCode);
        List<OfferDTO> offerDTOS = new ArrayList<>();
        for (Offer o:
             findWithSort) {
            OfferDTO offerDTO = modelMapper.map(o, OfferDTO.class);
            offerDTO.setOrderId(orderId);
            offerDTO.setNationalCode(nationalCode);
            offerDTOS.add(offerDTO);
        }
        return offerDTOS;
    }
}