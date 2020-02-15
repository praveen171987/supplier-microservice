package com.ashraya.supplier.controller;


import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashraya.supplier.LoggerService;
import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.domain.GeoLocationPayload;
import com.ashraya.supplier.domain.OrderResponse;
import com.ashraya.supplier.domain.UpdateOrderPayload;
import com.ashraya.supplier.service.OrderService;

@RestController
@RequestMapping(Constants.SUPPLIER)
public class OrderController {

	@Autowired
	private OrderService orderService;

	private LoggerService log = LoggerService.createLogger(OrderController.class.getName());

	@PostMapping(value = Constants.VERSION + Constants.UPDATE_ORDER)
	public OrderResponse updateOrder(@RequestBody UpdateOrderPayload updateOrderPayload) throws ParseException {
		log.printStart("updateOrder :" + "updateOrderPayload ::" + updateOrderPayload);
		return orderService.updateOrder(updateOrderPayload);
	}

	@PostMapping(value = Constants.VERSION + Constants.SAVE_GEOLOCATION)
	public OrderResponse saveGeoLocation(@RequestBody GeoLocationPayload geoLocationPayload) throws ParseException {
		log.printStart("geoLocation :" + "geoLocationPayload ::" + geoLocationPayload);
		return orderService.saveGeoloaction(geoLocationPayload);
	}
}
