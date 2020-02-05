package com.ashraya.supplier.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryGeoLocationDto {

	private Double latitute;

	private Double longitute;
	
	private float radius;

	private Integer recipientId;

	private Double speed;

	private Double bearing;

	private Double accuracy;

	private String address;

	private String provider;
}
