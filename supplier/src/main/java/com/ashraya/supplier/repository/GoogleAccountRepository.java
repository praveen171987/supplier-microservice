package com.ashraya.supplier.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ashraya.supplier.model.GoogleAccountInfo;

public interface GoogleAccountRepository extends CrudRepository<GoogleAccountInfo, Integer> {

    @Query("SELECT id FROM GoogleAccountInfo c WHERE c.emailId = :emailId")
    Integer findByEmail(@Param("emailId") String emailId);

}
