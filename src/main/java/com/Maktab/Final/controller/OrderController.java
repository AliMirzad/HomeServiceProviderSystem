package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.OrderDTO;
import com.Maktab.Final.model.entity.Order;
import com.Maktab.Final.model.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper = new ModelMapper();

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/create")
    public void create(@Valid @RequestBody OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        orderService.create(order, orderDTO.getNationalCode(), orderDTO.getSubServiceName());
    }
}
