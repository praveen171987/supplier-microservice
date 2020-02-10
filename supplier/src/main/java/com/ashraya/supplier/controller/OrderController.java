package com.ashraya.supplier.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashraya.supplier.LoggerService;
import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.domain.OrderResponse;
import com.ashraya.supplier.service.OrderService;

@RestController
@RequestMapping(Constants.SUPPLIER)
public class OrderController {

    @Autowired
    private OrderService orderService;

    private LoggerService log = LoggerService.createLogger(OrderController.class.getName());

    @GetMapping(value = Constants.VERSION + Constants.UPDATE_ORDER)
    public OrderResponse updateOrder(@PathVariable("bookingId") Integer bookingId, @PathVariable("status") String status) throws ParseException {
        log.printStart("updateOrder :" + "bookingId ::" + bookingId + "status ::" + status);
        return orderService.updateOrder(bookingId, status);
    }
}
