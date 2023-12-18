package com.example.finalproject.service;

import com.example.finalproject.model.ReservedRoom;
import com.example.finalproject.model.Resident;
import com.example.finalproject.model.Room;
import com.example.finalproject.model.model_enum.EmployeeType;
import com.example.finalproject.model.model_enum.ReservedRoomState;
import com.example.finalproject.repository.ReservedRoomRepository;
import com.example.finalproject.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ResidentService {

    private final ResidentRepository residentRepository;

    private final ReservedRoomRepository reservedRoomRepository;

    private Resident resident;

    /**
     * Method to get a resident by given email in Resident Repository
     */
    public Resident getResidentsByEmail(String email){
        return residentRepository.findByEmail(email);
    }

    /**
     * Method for Resident to create a reservation
     */
    public void residentReserve(LocalDate startDate, LocalDate endDate, Resident resident, Room room){
        ReservedRoom reservedRoom = ReservedRoom.builder()
                .room(room)
                .resident(resident)
                .startDate(startDate)
                .endDate(endDate)
                .state(ReservedRoomState.RESERVED)
                .rent(room.getRoomPrice())
                .build();
        reservedRoomRepository.save(reservedRoom);
    }

    /**
     * Method for Resident to cancel a reservation
     */
    public void cancelReserve(ReservedRoom reservedRoom){
        if(reservedRoomRepository.findBy(reservedRoom.getId()).isPresent() && resident.getReservedRooms().contains(reservedRoom)){
            reservedRoom.setState(ReservedRoomState.CANCELED);
            reservedRoomRepository.save(reservedRoom);
        }
    }
}
