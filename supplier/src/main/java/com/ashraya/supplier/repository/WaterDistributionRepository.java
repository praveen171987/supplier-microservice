package com.ashraya.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashraya.supplier.model.WaterDistribution;

@Repository
public interface WaterDistributionRepository extends JpaRepository<WaterDistribution, Integer>{
	
	public WaterDistribution findByWaterSupplierSupplierId(Integer distributionId);
}
