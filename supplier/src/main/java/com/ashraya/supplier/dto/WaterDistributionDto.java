package com.ashraya.supplier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaterDistributionDto {

	private Integer userId;
	
	private Integer supplierId; 
	
	private String dateTime;
	
	private Integer tankerCategoryId;
	
	private DeliveryGeoLocationDto deliveryGeoLocationDto;
}
