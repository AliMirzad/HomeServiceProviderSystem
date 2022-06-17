package com.Maktab.Final.controller;

//import com.Maktab.Final.config.SecurityUtil;
import com.Maktab.Final.controller.dto.OrderDTO;
import com.Maktab.Final.controller.dto.OrderSearchDTO;
import com.Maktab.Final.model.entity.Order;
//import com.Maktab.Final.model.entity.baseEntity.User;
import com.Maktab.Final.model.entity.enums.OrderStatus;
import com.Maktab.Final.model.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
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

    //TODO: mn
    @PostMapping("/order/create")
    public void create(@Valid @RequestBody OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
//        User test = SecurityUtil.getCurrentUser();
//        orderService.create(order, test.getNationalCode(), orderDTO.getSubServiceName());
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

    @GetMapping("/find+By+Done+And+Selected+Status/")
    public List<OrderDTO> findByDoneAndSelectedStatus() {
        List<Order> orders = orderService.findByDoneAndSelectedStatus();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order o:
                orders) {
            OrderDTO orderDTO = modelMapper.map(o, OrderDTO.class);
            orderDTO.setNationalCode(o.getCustomer().getNationalCode());
            orderDTO.setStatus(String.valueOf(o.getStatus()));
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    @GetMapping("/find+By+Time+And+Status+And+SubService+And+Service/")
    public List<OrderDTO> findByTimeAndStatusAndSubServiceAndService(@RequestBody OrderSearchDTO orderSearchDTO) {
        List<Order> orders = orderService.findByTimeAndStatusAndSubServiceAndService(orderSearchDTO.getStartTime(),
                orderSearchDTO.getEndTime(),
                orderSearchDTO.getStatus(),
                orderSearchDTO.getSubService(),
                orderSearchDTO.getServiceName());
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order o:
                orders) {
            OrderDTO orderDTO = modelMapper.map(o, OrderDTO.class);
            orderDTO.setNationalCode(o.getCustomer().getNationalCode());
            orderDTO.setStatus(String.valueOf(o.getStatus()));
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }
}
