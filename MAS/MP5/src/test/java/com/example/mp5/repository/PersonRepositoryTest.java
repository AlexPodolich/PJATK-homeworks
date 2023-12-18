package com.example.mp5.repository;

import com.example.mp5.model.Employee;
import com.example.mp5.model.Resident;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ResidentRepository residentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Employee e1, e2;
    Resident r1, r2;

    @BeforeEach
    public void initData(){
        e1 = Employee.builder()
                .name("emp1")
                .surname("surname1")
                .workingPhoneNumbers(new HashSet<>(Arrays.asList("+0675329590", "+0955639590")))
                .build();
        e2 = Employee.builder()
                .name("emp2")
                .surname("surname2")
                .workingPhoneNumbers(new HashSet<>(Arrays.asList("+0675329591", "+0955639591")))
                .build();

        r1 = Resident.builder()
                .name("res1")
                .surname("surname1")
                .residentPhoneNumber("+0675329592")
                .residentEmail("adpodol@gmail.com")
                .build();

        r2 = Resident.builder()
                .name("res2")
                .surname("surname2")
                .residentPhoneNumber("+0675329593")
                .residentEmail("adpodolich@gmail.com")
                .build();
    }
    @Test
    public void testRequiredDependencies(){
        assertNotNull(personRepository);
        assertNotNull(employeeRepository);
        assertNotNull(residentRepository);
    }

    @Test
    public void testSaveAll(){
        employeeRepository.saveAll(Arrays.asList(e1,e2));
        residentRepository.saveAll(Arrays.asList(r1,r2));
        entityManager.flush();
        assertEquals(4,personRepository.count());
    }

    @Test
    public void removeAll(){
        testSaveAll();

        entityManager.remove(e1);
        entityManager.remove(e2);
        entityManager.remove(r1);
        entityManager.remove(r2);
        entityManager.flush();

        assertEquals(0,personRepository.count());
    }
}