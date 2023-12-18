package com.example.finalproject.service;

import com.example.finalproject.model.Employee;
import com.example.finalproject.model.ReservedRoom;
import com.example.finalproject.model.Resident;
import com.example.finalproject.model.Room;
import com.example.finalproject.model.model_enum.EmployeeType;
import com.example.finalproject.model.model_enum.ReservedRoomState;
import com.example.finalproject.repository.EmployeeRepository;
import com.example.finalproject.repository.ReservedRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final ReservedRoomRepository reservedRoomRepository;

    private Employee employee;

    /**
     * Methods for changing type for dynamic inheritance
     */
    public void changeTypeToAdmin(Employee employee, String responsibility){
        if(!employee.getEmpType().contains(EmployeeType.ADMIN)){
            employee.getEmpType().add(EmployeeType.ADMIN);
            employee.addAdminResponsibility(responsibility);
            employeeRepository.save(employee);
        }
    }

    public void changeTypeToSecurity(Employee employee, String patrolArea){
        if(!employee.getEmpType().contains(EmployeeType.SECURITY)){
            employee.getEmpType().add(EmployeeType.SECURITY);
            employee.setPatrolArea(patrolArea);
            employeeRepository.save(employee);
        }
    }

    public void changeTypeToCleaningStuff(Employee employee, String cleaningArea){
        if(!employee.getEmpType().contains(EmployeeType.CLEANING_STUFF)){
            employee.getEmpType().add(EmployeeType.CLEANING_STUFF);
            employee.setCleaningArea(cleaningArea);
            employeeRepository.save(employee);
        }
    }

    /**
     * Methods for removing type for dynamic inheritance
     */
    public void removeAdminType(Employee employee){
        if(employee.getEmpType().size() > 1){
            if(employee.getEmpType().contains(EmployeeType.ADMIN)){
                employee.getEmpType().remove(EmployeeType.ADMIN);
                employee.removeAdminResponsibilities();
                employeeRepository.save(employee);
            }
        }
    }

    public void removeSecurityType(Employee employee){
        if(employee.getEmpType().size() > 1) {
            if (employee.getEmpType().contains(EmployeeType.SECURITY)) {
                employee.getEmpType().remove(EmployeeType.SECURITY);
                employee.setPatrolArea(null);
                employeeRepository.save(employee);
            }
        }
    }

    public void removeCleaningStuffType(Employee employee){
        if(employee.getEmpType().size() > 1) {
            if (employee.getEmpType().contains(EmployeeType.CLEANING_STUFF)) {
                employee.getEmpType().remove(EmployeeType.CLEANING_STUFF);
                employee.setCleaningArea(null);
                employeeRepository.save(employee);
            }
        }
    }

    /**
     * Method to get employees by given name from Employee repository
     */
    public List<Employee> getEmployeesByName(String searchedName){
        if(searchedName == null){
            throw new IllegalArgumentException("searched name is required");
        }

        return employeeRepository.findByName(searchedName);
    }


    /**
     * Method for Admin to create a reservation
     */
    public void adminReserve(LocalDate startDate, LocalDate endDate, Resident resident, Room room){
        if(employee.getEmpType().contains(EmployeeType.ADMIN)){
            ReservedRoom reservedRoom = ReservedRoom.builder()
                    .room(room)
                    .resident(resident)
                    .startDate(startDate)
                    .endDate(endDate)
                    .state(ReservedRoomState.RESERVED)
                    .rent(room.getRoomPrice())
                    .build();
            reservedRoomRepository.save(reservedRoom);
        }
    }

    /**
     * Method for Admin to cancel a reservation
     */
    public void cancelReserve(ReservedRoom reservedRoom){
        if(employee.getEmpType().contains(EmployeeType.ADMIN)){
            if(reservedRoomRepository.findBy(reservedRoom.getId()).isPresent()){
                reservedRoom.setState(ReservedRoomState.CANCELED);
                reservedRoomRepository.save(reservedRoom);
            }
        }
    }


}
