package com.example.mp5.repository;

import com.example.mp5.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservedRoomRepositoryTest {
    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private DormitoryRepository dormitoryRepository;
    @Autowired
    private ReservedRoomRepository reservedRoomRepository;

    @PersistenceContext
    private EntityManager entityManager;
    Resident res;
    Room room;
    ReservedRoom resRoom;
    Dormitory d1;

    @BeforeEach
    public void initData(){
        res = Resident.builder()
                .name("res1")
                .surname("surname1")
                .residentPhoneNumber("+0675329592")
                .residentEmail("adpodol@gmail.com")
                .build();

        d1 = Dormitory.builder()
                .name("name3")
                .dormitoryRating(DormitoryRating._5)
                .address("street1")
                .build();

        room = Room.builder()
                .roomNumber("204B")
                .roomPrice(100)
                .roomDescription("desc1")
                .roomSize(100)
                .dormitory(d1)
                .build();



    }

    @Test
    public void testRequiredDependencies(){
        assertNotNull(roomRepository);
        assertNotNull(residentRepository);
        assertNotNull(reservedRoomRepository);
    }

    @Test
    public void testSaveAll(){
        dormitoryRepository.save(d1);
        residentRepository.save(res);
        roomRepository.save(room);
        entityManager.flush();
        assertEquals(1,residentRepository.count());
        assertEquals(1,roomRepository.count());
    }

    @Test
    public void addReservedRoom(){
        testSaveAll();
        resRoom = ReservedRoom.builder()
                .room(room)
                .resident(res)
                .startDate(LocalDate.of(2023, 8, 10))
                .endDate(LocalDate.of(2023, 11, 10))
                .build();

        reservedRoomRepository.save(resRoom);
        res.getReservedRooms().add(resRoom);
        room.getReservedRooms().add(resRoom);
        residentRepository.save(res);
        roomRepository.save(room);
        entityManager.flush();

        assertEquals(1,reservedRoomRepository.count());

        Iterable<Room> rooms = roomRepository.findAll();
        for (Room room : rooms){
            System.out.println(room.getReservedRooms());
            assertEquals(1, room.getReservedRooms().size());
        }
        Iterable<Resident> residents = residentRepository.findAll();
        for (Resident resident : residents){
            System.out.println(resident.getReservedRooms());
            assertEquals(1, resident.getReservedRooms().size());
        }

        Optional<ReservedRoom> byId = reservedRoomRepository.findById(resRoom.getId());
        assertTrue(byId.isPresent());
        System.out.println(byId.get().getRoom());
    }

    @Test
    public void removeResRoom(){
        addReservedRoom();
        res.getReservedRooms().remove(resRoom);
        room.getReservedRooms().remove(resRoom);
        entityManager.remove(resRoom);
        roomRepository.save(room);
        residentRepository.save(res);
        entityManager.flush();

        Iterable<ReservedRoom> reservedRooms = reservedRoomRepository.findAll();
        Set<ReservedRoom> reservedRoomSet = new HashSet<>();
        reservedRooms.forEach(reservedRoomSet::add);
        assertEquals(0, reservedRoomSet.size());

        Iterable<Resident> residents = residentRepository.findAll();
        for (Resident resident : residents){
            System.out.println(resident.getReservedRooms());
            assertEquals(0, resident.getReservedRooms().size());
        }

        Iterable<Room> rooms = roomRepository.findAll();
        for (Room room : rooms){
            System.out.println(room.getReservedRooms());
            assertEquals(0, room.getReservedRooms().size());
        }
    }

    @Test
    public void removeRoom(){
        testSaveAll();
        addReservedRoom();

        Iterable<ReservedRoom> reservedRooms = reservedRoomRepository.findAll();
        Set<ReservedRoom> reservedRoomSet = new HashSet<>();
        reservedRooms.forEach(reservedRoomSet::add);
        System.out.println(reservedRoomSet.size());
        assertEquals(1, reservedRoomSet.size());

        Iterable<Room> rooms = roomRepository.findAll();
        Set<Room> roomSet = new HashSet<>();
        rooms.forEach(roomSet::add);
        assertEquals(1, roomSet.size());

        entityManager.remove(room);

        reservedRooms = reservedRoomRepository.findAll();
        reservedRoomSet = new HashSet<>();
        reservedRooms.forEach(reservedRoomSet::add);
        assertEquals(0, reservedRoomSet.size());

        rooms = roomRepository.findAll();
        roomSet = new HashSet<>();
        rooms.forEach(roomSet::add);
        System.out.println(roomSet.size());
        assertEquals(0, roomSet.size());
    }

    @Test
    public void removeResident(){
        testSaveAll();
        addReservedRoom();

        Iterable<ReservedRoom> reservedRooms = reservedRoomRepository.findAll();
        Set<ReservedRoom> reservedRoomSet = new HashSet<>();
        reservedRooms.forEach(reservedRoomSet::add);
        System.out.println(reservedRoomSet.size());
        assertEquals(1, reservedRoomSet.size());

        Iterable<Resident> residents = residentRepository.findAll();
        Set<Resident> residentSet = new HashSet<>();
        residents.forEach(residentSet::add);
        assertEquals(1, residentSet.size());

        entityManager.remove(res);

        residents = residentRepository.findAll();
        residentSet = new HashSet<>();
        residents.forEach(residentSet::add);
        assertEquals(0, residentSet.size());


        reservedRooms = reservedRoomRepository.findAll();
        reservedRoomSet = new HashSet<>();
        reservedRooms.forEach(reservedRoomSet::add);
        System.out.println(reservedRoomSet.size());
        assertEquals(0, reservedRoomSet.size());
    }
}