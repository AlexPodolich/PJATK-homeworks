package com.example.finalproject.repository;

import com.example.finalproject.model.Employment;
import com.example.finalproject.model.ReservedRoom;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReservedRoomRepository extends CrudRepository<ReservedRoom, Long> {
    @Query("from ReservedRoom as rr left join fetch rr.room where rr.id = :id")
    public Optional<ReservedRoom> findBy(@Param("id") Long id);
}
