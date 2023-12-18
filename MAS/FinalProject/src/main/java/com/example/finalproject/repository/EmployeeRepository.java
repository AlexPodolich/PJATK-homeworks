package com.example.finalproject.repository;

import com.example.finalproject.model.Dormitory;
import com.example.finalproject.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public List<Employee> findByName(String name);

}
