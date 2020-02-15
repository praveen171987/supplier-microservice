package com.ashraya.supplier.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeoLocationPayload {

	private Integer userId;

	private Double latitude;

	private Double longitude;

	private Float speed;

	private Float bearing;

	private Float accuracy;

	private String address;

	private String provider;

	private Double distance;
	
	private String dateTime;
}
