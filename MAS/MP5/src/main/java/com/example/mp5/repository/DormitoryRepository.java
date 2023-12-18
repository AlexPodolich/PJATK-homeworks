package com.example.mp5.repository;

import com.example.mp5.model.Dormitory;
import com.example.mp5.model.DormitoryRating;
import com.example.mp5.repository.DormitoryRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DormitoryRepository extends CrudRepository<Dormitory, Long> {
    public List<Dormitory> findByName(String name);

    @Query("from Dormitory as d where d.dormitoryRating > :minRating")
    public List<Dormitory> findDormitoryWithRatingGreaterThan(@Param("minRating") DormitoryRating minRating);

    @Query("from Dormitory d where d.address = :street")
    public List<Dormitory> findByStreetAddress(@Param("street")String street);
}
