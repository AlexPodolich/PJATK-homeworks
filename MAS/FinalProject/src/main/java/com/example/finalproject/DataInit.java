package com.example.finalproject;

import com.example.finalproject.model.*;
import com.example.finalproject.model.model_enum.DormitoryRating;
import com.example.finalproject.model.model_enum.PersonHealth;
import com.example.finalproject.repository.DormitoryRepository;
import com.example.finalproject.repository.OwnerRepository;
import com.example.finalproject.repository.ResidentRepository;
import com.example.finalproject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final DormitoryRepository dormitoryRepository;
    private final OwnerRepository ownerRepository;
    private final RoomRepository roomRepository;
    private final ResidentRepository residentRepository;

    @EventListener
    public void atStart(ContextRefreshedEvent ev){
        if(isDatabaseEmpty()){
            Resident resident = Resident.builder()
                    .name("Sashka")
                    .surname("Podolich")
                    .residentEmail("adpodol@gmail.com")
                    .health(PersonHealth.HEALTHY)
                    .medicalCard("medicalCard")
                    .residentPhoneNumber("+380675329590")
                    .build();
            Owner owner = Owner.builder()
                    .name("Alex")
                    .surname("Marquizy")
                    .health(PersonHealth.HEALTHY)
                    .medicalCard("medicalCard")
                    .dormitories(new HashSet<>())
                    .build();
            Dormitory dormitory1 = Dormitory.builder()
                    .address(
                            Address.builder()
                                    .street("Suwak")
                                    .number(13).build())
                    .name("StudentDepot")
                    .dormitoryRating(DormitoryRating._5)
                    .owner(owner)
                    .build();
            Dormitory dormitory2 = Dormitory.builder()
                    .address(
                            Address.builder()
                                    .street("Suwak")
                                    .number(15).build())
                    .name("StudentDepot2")
                    .dormitoryRating(DormitoryRating._4)
                    .owner(owner)
                    .build();

            Room room1 = Room.builder().roomNumber("B204").roomDescription("Cozy and spacious room").roomPrice(2000).roomSize(30).dormitory(dormitory1).build();
            Room room2 = Room.builder().roomNumber("B206").roomDescription("Stylishly designed room").roomPrice(3000).roomSize(40).dormitory(dormitory1).build();
            Room room3 = Room.builder().roomNumber("B120").roomDescription("Comfortable retreat for a peaceful stay.").roomPrice(5000).roomSize(50).dormitory(dormitory1).build();
            Room room4 = Room.builder().roomNumber("DZ06").roomDescription("Luxurious room").roomPrice(6000).roomSize(60).dormitory(dormitory1).build();
            Room room5 = Room.builder().roomNumber("8804").roomDescription("Charming with a cozy ambiance.").roomPrice(7008).roomSize(70).dormitory(dormitory1).build();
            Room room6 = Room.builder().roomNumber("1303").roomDescription("Calming atmosphere for relaxation.").roomPrice(8000).roomSize(80).dormitory(dormitory1).build();

            Room room7 = Room.builder().roomNumber("A106").roomDescription("Contemporary room").roomPrice(4000).roomSize(90).dormitory(dormitory2).build();
            Room room8 = Room.builder().roomNumber("A206").roomDescription("Bright and airy accommodation").roomPrice(5000).roomSize(40).dormitory(dormitory2).build();

            ownerRepository.save(owner);
            dormitoryRepository.save(dormitory1);
            dormitoryRepository.save(dormitory2);

            roomRepository.save(room1);
            roomRepository.save(room2);
            roomRepository.save(room3);
            roomRepository.save(room4);
            roomRepository.save(room5);
            roomRepository.save(room6);
            roomRepository.save(room7);
            roomRepository.save(room8);

            residentRepository.save(resident);
        }
    }

    private boolean isDatabaseEmpty() {
        return ownerRepository.count() == 0 && dormitoryRepository.count() == 0 && roomRepository.count() == 0 && residentRepository.count() == 0;
    }
}
