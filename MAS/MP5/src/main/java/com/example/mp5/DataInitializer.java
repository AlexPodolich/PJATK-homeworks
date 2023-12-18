package com.example.mp5;

import com.example.mp5.model.Dormitory;
import com.example.mp5.model.DormitoryRating;
import com.example.mp5.model.Employee;
import com.example.mp5.model.Employment;
import com.example.mp5.repository.DormitoryRepository;
import com.example.mp5.repository.EmployeeRepository;
import com.example.mp5.repository.EmploymentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final DormitoryRepository dormitoryRepository;
    private final EmployeeRepository employeeRepository;
    private final EmploymentRepository employmentRepository;
    Dormitory d1;
    Employee e1;
    Employment employment;
    @EventListener
    public void atStart(ContextRefreshedEvent ev){
        System.out.println("Context refreshed!");
//
//        d1 = Dormitory.builder()
//                .name("name3")
//                .dormitoryRating(DormitoryRating._5)
//                .address("street1")
//                .build();
//
//        e1 = Employee.builder()
//                .name("emp1")
//                .surname("surname1")
//                .workingPhoneNumbers(new HashSet<>(Arrays.asList("+0675329590", "+0955639590")))
//                .build();
//
//        employment = Employment.builder()
//                .startDate(LocalDate.of(2002, 10, 10))
//                .endDate(LocalDate.of(2003, 10, 10))
//                .dormitory(d1)
//                .employee(e1)
//                .monthlySalary(3000)
//                .build();
//
//
//        dormitoryRepository.save(d1);
//        employeeRepository.save(e1);
//
//        d1.getEmployments().add(employment);
//        employmentRepository.save(employment);
//
//        dormitoryRepository.save(d1);
//
//        d1.getEmployments().remove(employment);
//        e1.getEmployments().remove(employment);
//        employeeRepository.save(e1);
//        dormitoryRepository.save(d1);
//        System.out.println(d1.getEmployments());
//        System.out.println(e1.getEmployments());
    }

}
