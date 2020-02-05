package com.ashraya.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashraya.supplier.model.DeliveryGeoLocation;

public interface DeliveryGeoLocationRepository extends JpaRepository<DeliveryGeoLocation, Integer> {

}
