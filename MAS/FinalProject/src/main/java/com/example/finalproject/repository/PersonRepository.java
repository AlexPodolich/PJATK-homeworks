package com.example.finalproject.repository;

import com.example.finalproject.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
