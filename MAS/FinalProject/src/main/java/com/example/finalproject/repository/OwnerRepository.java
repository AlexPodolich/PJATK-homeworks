package com.example.finalproject.repository;

import com.example.finalproject.model.Employment;
import com.example.finalproject.model.Owner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
