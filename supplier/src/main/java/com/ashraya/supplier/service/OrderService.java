package com.ashraya.supplier.service;


import java.text.ParseException;

import com.ashraya.supplier.domain.GeoLocationPayload;
import com.ashraya.supplier.domain.OrderResponse;
import com.ashraya.supplier.domain.UpdateOrderPayload;

public interface OrderService {

	public OrderResponse updateOrder(UpdateOrderPayload updateOrderPayload) throws ParseException;
	
	public OrderResponse saveGeoloaction(GeoLocationPayload geoLocationPayload) throws ParseException;
	
}
