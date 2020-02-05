package com.ashraya.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashraya.supplier.model.WaterTanker;

@Repository
public interface WaterTankerRepository extends JpaRepository<WaterTanker, Integer>{

}
