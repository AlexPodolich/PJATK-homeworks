package com.example.finalproject.repository;
import com.example.finalproject.model.Employment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmploymentRepository extends CrudRepository<Employment, Long> {

    @Query("from Employment as e left join fetch e.employee where e.id = :id")
    public Optional<Employment> findBy(@Param("id") Long id);
}
