package com.example.mp5.repository;

import com.example.mp5.model.Employment;
import com.example.mp5.model.ReservedRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReservedRoomRepository extends CrudRepository<ReservedRoom, Long> {
    @Query("from ReservedRoom as rr left join fetch rr.room where rr.id = :id")
    public Optional<ReservedRoom> findBy(@Param("id") Long id);


}
