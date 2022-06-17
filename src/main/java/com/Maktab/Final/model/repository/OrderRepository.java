package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.*;
import com.Maktab.Final.model.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderById(Integer id);

    List<Order> findOrderByCustomer(Customer customer);

    List<Order> findOrderBySubService(SubService subService);

    @Query("select o from Order o where o.status = 'selection' or o.status = 'done'")
    List<Order> findByDoneAndSelectedStatus();

    @Query("select o from Order o " +
            "where (o.orderRegisterTime between :startTime and :endTime)" +
            "and o.status = :status " +
            "and o.subService.name = :subService " +
            "and o.subService.services.name = :serviceName")
    List<Order> findByTimeAndStatusAndSubServiceAndService(@Param("startTime") LocalDateTime startTime,
                                                           @Param("endTime") LocalDateTime endTime,
                                                           @Param("status") OrderStatus status,
                                                           @Param("subService") String subService,
                                                           @Param("serviceName") String serviceName);
}
