package com.example.finalproject.service;

import com.example.finalproject.model.Dormitory;
import com.example.finalproject.model.Room;
import com.example.finalproject.repository.DormitoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DormitoryService {
    private final DormitoryRepository dormitoryRepository;

    /**
     * Method to get all dormitories from dormitory repository
     */
    public List<Dormitory> getAllDormitories(){
        return dormitoryRepository.findAll();
    }

    /**
     * Method to get a dormitory by given ID from dormitory repository
     */
    public Dormitory getDormitoryById(Long dormId){
        return dormitoryRepository.findById(dormId).get();
    }

    /**
     * Method to get dormitories ordered by name
     */
    public List<Dormitory> getDormsOrderedByName(){
        return dormitoryRepository.findDormsOrderedByName();
    }

    /**
     * Method to get all rooms in a dormitory by given ID from dormitory repository and ordered constraint (by room number)
     */
    public Set<Room> getRoomsOfDormitoryById(Long dormId) {
        Set<Room> orderedRooms = dormitoryRepository.findById(dormId).get().getRooms().stream().sorted(Comparator.comparing(Room::getRoomNumber, String.CASE_INSENSITIVE_ORDER)).collect(Collectors.toCollection(LinkedHashSet::new));
        return orderedRooms;
    }
}
