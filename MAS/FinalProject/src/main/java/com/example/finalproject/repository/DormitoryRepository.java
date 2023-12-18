package com.example.finalproject.repository;

import com.example.finalproject.model.Dormitory;
import com.example.finalproject.model.model_enum.DormitoryRating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DormitoryRepository extends CrudRepository<Dormitory, Long> {

    public List<Dormitory> findAll();

    public List<Dormitory> findByName(String name);

    @Query("from Dormitory as d where d.dormitoryRating > :minRating")
    public List<Dormitory> findDormitoryWithRatingGreaterThan(@Param("minRating") DormitoryRating minRating);

    @Query("from Dormitory d where d.address = :street")
    public List<Dormitory> findByStreetAddress(@Param("street")String street);

    @Query("SELECT d FROM Dormitory d ORDER BY d.name")
    public List<Dormitory> findDormsOrderedByName();
}
