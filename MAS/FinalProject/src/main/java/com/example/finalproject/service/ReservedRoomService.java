package com.example.finalproject.service;

import com.example.finalproject.model.ReservedRoom;
import com.example.finalproject.model.Resident;
import com.example.finalproject.model.Room;
import com.example.finalproject.model.model_enum.ReservedRoomState;
import com.example.finalproject.repository.DormitoryRepository;
import com.example.finalproject.repository.ReservedRoomRepository;
import com.example.finalproject.repository.ResidentRepository;
import com.example.finalproject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ReservedRoomService {
    private final ReservedRoomRepository reservedRoomRepository;

    /**
     * Method for creating a reservation(object of Class ReservedRoom) in ReservedRoom Repository
     */
    public void makeReservation(String startDate, String endDate, Resident resident, Room room){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateStart = LocalDate.parse(startDate, formatter);
        LocalDate dateEnd = LocalDate.parse(endDate, formatter);
        ReservedRoom reservedRoom = ReservedRoom.builder()
                .room(room)
                .resident(resident)
                .startDate(dateStart)
                .endDate(dateEnd)
                .state(ReservedRoomState.RESERVED)
                .rent(room.getRoomPrice())
                .build();
        reservedRoomRepository.save(reservedRoom);
    }

}
