package com.example.mp5.repository;

import com.example.mp5.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ResidentRepositoryTest {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ResidentRepository residentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Report rep1, rep2;
    Resident r1;


    @BeforeEach
    public void initData(){
        rep1 = Report.builder()
                .reportDescription("desc1")
                .reportedRoom("room1")
                .build();
        rep2 = Report.builder()
                .reportDescription("desc2")
                .reportedRoom("room2")
                .build();

        r1 = Resident.builder()
                .name("res1")
                .surname("surname1")
                .residentPhoneNumber("+0675329592")
                .residentEmail("adpodol@gmail.com")
                .build();
    }

    @Test
    public void testRequiredDependencies(){
        assertNotNull(reportRepository);
        assertNotNull(residentRepository);
    }

    @Test
    public void testSave(){
        residentRepository.saveAll(Arrays.asList(r1));
        entityManager.flush();
        assertEquals(1,residentRepository.count());
    }

    @Test
    public void addReports(){
        testSave();
        r1.getReports().add(rep1);
        r1.getReports().add(rep2);

        rep1.setResident(r1);
        rep2.setResident(r1);
        reportRepository.save(rep1);
        reportRepository.save(rep2);
        residentRepository.save(r1);

        Iterable<Resident> residents = residentRepository.findAll();
        for (Resident resident : residents){
            System.out.println(resident.getReports());
            assertEquals(2, resident.getReports().size());
        }

        Iterable<Report> reports = reportRepository.findAll();
        for (Report report : reports){
            System.out.println(report.getResident());
            assertNotNull(report.getResident());
        }
    }

    @Test
    public void removeReports(){
        addReports();

        r1.getReports().remove(rep1);
        r1.getReports().remove(rep2);
        entityManager.remove(rep1);
        entityManager.remove(rep2);
        residentRepository.save(r1);
        entityManager.flush();

        Iterable<Report> reports = reportRepository.findAll();
        Set<Report> reportSet = new HashSet<>();
        reports.forEach(reportSet::add);
        assertEquals(0, reportSet.size());


        Iterable<Resident> residents = residentRepository.findAll();
        for (Resident resident : residents){
            System.out.println(resident.getReports());
            assertEquals(0, resident.getReports().size());
        }
    }
}