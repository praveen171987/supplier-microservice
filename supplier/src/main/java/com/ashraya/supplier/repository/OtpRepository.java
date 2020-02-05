package com.ashraya.supplier.repository;

import org.springframework.data.repository.CrudRepository;

import com.ashraya.supplier.model.Otp;

public interface OtpRepository extends CrudRepository<Otp, Integer> {

    public Otp findByWaterSupplierSupplierIdAndOtpNumber(Integer userId, String otpNumber);

}
