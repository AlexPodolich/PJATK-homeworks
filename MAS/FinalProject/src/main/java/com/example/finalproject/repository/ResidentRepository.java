package com.example.finalproject.repository;

import com.example.finalproject.model.Dormitory;
import com.example.finalproject.model.Resident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResidentRepository extends CrudRepository<Resident, Long> {
    @Query("from Resident r where r.residentEmail = :email")
    public Resident findByEmail(@Param("email")String email);
}
