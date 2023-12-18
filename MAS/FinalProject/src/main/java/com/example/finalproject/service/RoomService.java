package com.example.finalproject.service;

import com.example.finalproject.model.Room;
import com.example.finalproject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    /**
     * Method to get a room by given ID in Room Repository
     */
    public Room getRoomById(Long roomId){
        return roomRepository.findById(roomId).get();
    }
}
