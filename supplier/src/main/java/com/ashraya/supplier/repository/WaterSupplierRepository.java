package com.ashraya.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashraya.supplier.model.WaterSupplier;

@Repository
public interface WaterSupplierRepository extends JpaRepository<WaterSupplier, Integer> {
	
    public WaterSupplier findByMobileNumber(String mobileNumber);

    public WaterSupplier findByFacebookAccountInfoId(Integer id);

    public WaterSupplier findByGoogleAccountInfoId(Integer id);

	public WaterSupplier findBySupplierId(Integer id);
}
