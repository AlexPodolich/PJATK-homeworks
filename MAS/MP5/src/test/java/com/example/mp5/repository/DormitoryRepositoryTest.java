package com.example.mp5.repository;

import com.example.mp5.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class DormitoryRepositoryTest {
    @Autowired
    private DormitoryRepository dormitoryRepository;
    @Autowired
    private RoomRepository roomRepository;

    @PersistenceContext
    private EntityManager entityManager;
    Dormitory d1;
    Room room;
    @BeforeEach
    public void initData(){

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
        assertNotNull(dormitoryRepository);
    }

    @Test
    public void testFetchDormitories(){
        Iterable<Dormitory> dormitories = dormitoryRepository.findAll();
        for (Dormitory dormitory : dormitories){
            System.out.println(dormitory);
        }
    }

    @Test
    public void testSave(){
        dormitoryRepository.save(d1);
        entityManager.flush();
        long count = dormitoryRepository.count();
        assertEquals(1, count);
    }

    @Test
    public void testSaveInvalidDormName(){
        assertThrows(ConstraintViolationException.class, ()->{
            Dormitory dormitory = Dormitory.builder().name("1").build();
            dormitoryRepository.save(dormitory);
            entityManager.flush();
        });
    }

    @Test
    public void testFindByName(){
        testSave();
        List<Dormitory> dormitoryList = dormitoryRepository.findByName("name3");
        System.out.println(dormitoryRepository.count());
        System.out.println(dormitoryList);
        assertEquals(1, dormitoryList.size());
    }

    @Test
    public void testFindByBudgetGreaterThan(){
        testSave();
        List<Dormitory> dormitoryList = dormitoryRepository.findDormitoryWithRatingGreaterThan(DormitoryRating._4);
        System.out.println(dormitoryRepository.count());
        assertEquals(1, dormitoryList.size());
        System.out.println(dormitoryList);
    }

    @Test
    public void testFindByAddressStreet(){
        testSave();
        List<Dormitory> dormitoryList = dormitoryRepository.findByStreetAddress("street1");
        System.out.println(dormitoryRepository.count());
        System.out.println(dormitoryList);
        assertEquals(1, dormitoryList.size());
    }

    @Test
    public void addRoom(){
        testSave();
        room = Room.builder()
                .roomNumber("204B")
                .roomPrice(100)
                .roomDescription("desc1")
                .roomSize(100)
                .dormitory(d1)
                .build();

        d1.getRooms().add(room);

        roomRepository.save(room);
        dormitoryRepository.save(d1);


        Iterable<Dormitory> dormitories = dormitoryRepository.findAll();
        for (Dormitory dormitory : dormitories){
            System.out.println(dormitory.getRooms());
            assertEquals(1, dormitory.getRooms().size());
        }

        Iterable<Room> rooms = roomRepository.findAll();
        for (Room room1 : rooms){
            System.out.println(room1.getDormitory());
            assertNotNull(room1.getDormitory());
        }

    }

    @Test
    public void removeRoom(){
        addRoom();


        d1.getRooms().remove(room);
        dormitoryRepository.save(d1);

        entityManager.remove(room);
        entityManager.flush();

        Iterable<Room> rooms = roomRepository.findAll();
        Set<Room> roomSet = new HashSet<>();
        rooms.forEach(roomSet::add);
        assertEquals(0, roomSet.size());

        Iterable<Dormitory> dormitories = dormitoryRepository.findAll();
        for (Dormitory dormitory : dormitories){
            System.out.println(dormitory.getRooms());
            assertEquals(0, dormitory.getRooms().size());
        }
    }
}