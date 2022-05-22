package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.OrderDTO;
import com.Maktab.Final.model.entity.Order;
import com.Maktab.Final.model.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("order/customer+order/{customerNationalCode}")
    public List<OrderDTO> findCustomerOffers(@PathVariable(name = "customerNationalCode") String customerNationalCode) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order o:
                orderService.findOrderByCustomer(customerNationalCode)) {
            OrderDTO orderDTO = modelMapper.map(o, OrderDTO.class);
            orderDTO.setNationalCode(o.getCustomer().getNationalCode());
            orderDTO.setStatus(String.valueOf(o.getStatus()));
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }
}
