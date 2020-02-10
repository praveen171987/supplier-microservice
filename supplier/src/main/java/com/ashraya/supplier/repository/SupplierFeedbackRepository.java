package com.ashraya.supplier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashraya.supplier.model.SupplierFeedback;

@Repository
public interface SupplierFeedbackRepository extends JpaRepository<SupplierFeedback, Integer> {

    public List<SupplierFeedback> findByWaterDistributionIdOrWaterSupplierSupplierId(Integer orderId, Integer userId);
}
