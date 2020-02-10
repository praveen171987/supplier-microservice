package com.ashraya.supplier.service;

import java.text.ParseException;

import com.ashraya.supplier.domain.OrderResponse;

public interface OrderService {

	public OrderResponse updateOrder(Integer bookingId, String status) throws ParseException;
	
}
