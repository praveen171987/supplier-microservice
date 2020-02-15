package com.ashraya.supplier.domain;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderPayload {

	private Integer bookingId;
	
	private String staus;
	
	private Integer supplierId;
	
	private String dateTime;
	
	private GeoLocationPayload geoLocation;
}
