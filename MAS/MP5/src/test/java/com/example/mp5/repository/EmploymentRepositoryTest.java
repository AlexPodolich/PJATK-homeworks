package com.example.mp5.repository;

import com.example.mp5.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmploymentRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DormitoryRepository dormitoryRepository;
    @Autowired
    private EmploymentRepository employmentRepository;

    @PersistenceContext
    private EntityManager entityManager;
    Dormitory d1;
    Employee e1;
    Employment employment;

    @BeforeEach
    public void initData(){
        e1 = Employee.builder()
                .name("emp1")
                .surname("surname1")
                .workingPhoneNumbers(new HashSet<>(Arrays.asList("+0675329590", "+0955639590")))
                .build();


        d1 = Dormitory.builder()
                .name("name3")
                .dormitoryRating(DormitoryRating._5)
                .address("street1")
                .build();
    }

    @Test
    public void testRequiredDependencies(){
        assertNotNull(dormitoryRepository);
        assertNotNull(employeeRepository);
    }

    @Test
    public void testSaveAll(){
        employeeRepository.save(e1);
        dormitoryRepository.save(d1);
        entityManager.flush();
        assertEquals(1,employeeRepository.count());
        assertEquals(1,dormitoryRepository.count());
    }

    @Test
    public void addEmployment(){
        testSaveAll();
        employment = Employment.builder()
                .startDate(LocalDate.of(2002, 10, 10))
                .endDate(LocalDate.of(2003, 10, 10))
                .dormitory(d1)
                .employee(e1)
                .monthlySalary(3000)
                .build();

        employmentRepository.save(employment);
        d1.getEmployments().add(employment);
        e1.getEmployments().add(employment);
        employeeRepository.save(e1);
        dormitoryRepository.save(d1);
        entityManager.flush();

        assertEquals(1,employmentRepository.count());

        Iterable<Dormitory> dormitories = dormitoryRepository.findAll();
        for (Dormitory dormitory : dormitories){
            System.out.println(dormitory.getEmployments());
            assertEquals(1, dormitory.getEmployments().size());
        }
        Iterable<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees){
            System.out.println(employee.getEmployments());
            assertEquals(1, employee.getEmployments().size());
        }

        Optional<Employment> byId = employmentRepository.findById(employment.getId());
        assertTrue(byId.isPresent());
        System.out.println(byId.get().getEmployee());
    }

    @Test
    public void removeEmployment(){
        addEmployment();
        d1.getEmployments().remove(employment);
        e1.getEmployments().remove(employment);
        entityManager.remove(employment);
        employeeRepository.save(e1);
        dormitoryRepository.save(d1);
        entityManager.flush();

        Iterable<Employment> employments = employmentRepository.findAll();
        Set<Employment> employmentList = new HashSet<>();
        employments.forEach(employmentList::add);
        assertEquals(0, employmentList.size());

        Iterable<Dormitory> dormitories = dormitoryRepository.findAll();
        for (Dormitory dormitory : dormitories){
            System.out.println(dormitory.getEmployments());
            assertEquals(0, dormitory.getEmployments().size());
        }

        Iterable<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees){
            System.out.println(employee.getEmployments());
            assertEquals(0, employee.getEmployments().size());
        }
    }

    @Test
    public void removeDorm(){
        testSaveAll();
        addEmployment();

        Iterable<Employment> employments1 = employmentRepository.findAll();
        Set<Employment> employmentList1 = new HashSet<>();
        employments1.forEach(employmentList1::add);
        System.out.println(employmentList1.size());
        assertEquals(1, employmentList1.size());

        Iterable<Dormitory> employments = dormitoryRepository.findAll();
        Set<Dormitory> dormitorySet = new HashSet<>();
        employments.forEach(dormitorySet::add);
        assertEquals(1, dormitorySet.size());

        entityManager.remove(d1);

        employments = dormitoryRepository.findAll();
        dormitorySet = new HashSet<>();
        employments.forEach(dormitorySet::add);
        assertEquals(0, dormitorySet.size());

        Iterable<Employment> employments2 = employmentRepository.findAll();
        Set<Employment> employmentList = new HashSet<>();
        employments2.forEach(employmentList::add);
        System.out.println(employmentList.size());
        assertEquals(0, employmentList.size());
    }

    @Test
    public void removeEmp(){
        testSaveAll();
        addEmployment();

        Iterable<Employment> employments1 = employmentRepository.findAll();
        Set<Employment> employmentList1 = new HashSet<>();
        employments1.forEach(employmentList1::add);
        System.out.println(employmentList1.size());
        assertEquals(1, employmentList1.size());

        Iterable<Employee> employees = employeeRepository.findAll();
        Set<Employee> employeeSet = new HashSet<>();
        employees.forEach(employeeSet::add);
        assertEquals(1, employeeSet.size());

        entityManager.remove(e1);
        Iterable<Employee> employees1 = employeeRepository.findAll();
        Set<Employee> employeeSet1 = new HashSet<>();
        employees1.forEach(employeeSet1::add);
        assertEquals(0, employeeSet1.size());

        Iterable<Employment> employments2 = employmentRepository.findAll();
        Set<Employment> employmentList = new HashSet<>();
        employments2.forEach(employmentList::add);
        System.out.println(employmentList.size());
        assertEquals(0, employmentList.size());
    }

}