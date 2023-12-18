package com.example.finalproject.service;

import com.example.finalproject.model.*;
import com.example.finalproject.model.model_enum.DormitoryRating;
import com.example.finalproject.model.model_enum.ReservedRoomState;
import com.example.finalproject.repository.DormitoryRepository;
import com.example.finalproject.repository.EmploymentRepository;
import com.example.finalproject.repository.OwnerRepository;
import com.example.finalproject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final DormitoryRepository dormitoryRepository;
    private final RoomRepository roomRepository;
    private final EmploymentRepository employmentRepository;
    private Owner owner;

    /**
     * Method for creating a dormitory(object of Class Dormitory) in Dormitory Repository
     */
    public void createDorm(String name, Address address, DormitoryRating dormitoryRating){
        Dormitory dormitory = Dormitory.builder()
                .owner(owner)
                .name(name)
                .address(address)
                .dormitoryRating(dormitoryRating)
                .build();
        dormitoryRepository.save(dormitory);
    }

    /**
     * Method for creating a room(object of Class Room) in Room Repository
     */
    public void createRoom(Dormitory dormitory, String roomNumber, float roomPrice, String roomDescription, float roomSize){
        Room room = Room.builder()
                .dormitory(dormitory)
                .roomNumber(roomNumber)
                .roomPrice(roomPrice)
                .roomDescription(roomDescription)
                .roomSize(roomSize)
                .build();
        roomRepository.save(room);
    }

    /**
     * Method for creating an employment(object of Class Employment) in Employment Repository
     */
    public void addEmployment(Dormitory dormitory, Employee employee, float monthlySalary, LocalDate startDate, LocalDate endDate){
        Employment employment = Employment.builder()
                .dormitory(dormitory)
                .employee(employee)
                .startDate(startDate)
                .endDate(endDate)
                .monthlySalary(monthlySalary)
                .build();
        employmentRepository.save(employment);
    }
}
