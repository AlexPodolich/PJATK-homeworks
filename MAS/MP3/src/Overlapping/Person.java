package Overlapping;

import Project.ObjectPlus;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Person extends ObjectPlus {
    private long id;
    private String name;
    private String surname;
    private final Resident residentRole;
    private final Employee employeeRole;

    public Person(EnumSet<PersonType> types, // person types
                  long id, String name, String surname, //person fields
                  String residentPhoneNumber, String residentEmail, // for resident
                  String workingPhoneNumber // for Employee
    ) {
        validateTypes(types);
        setId(id);
        setName(name);
        setSurname(surname);


        if(types.contains(PersonType.RESIDENT)){
            if(residentPhoneNumber == null){
                throw new IllegalArgumentException("residentPhoneNumber is required");
            }
            if(residentEmail == null){
                throw new IllegalArgumentException("residentEmail is required");
            }
            residentRole = new Resident(this, residentPhoneNumber, residentEmail);
        }else{
            residentRole = null;
        }

        if(types.contains(PersonType.EMPLOYEE)){
            if(workingPhoneNumber == null){
                throw new IllegalArgumentException("workingPhoneNumber is required");
            }
            employeeRole = new Employee(this, workingPhoneNumber);
        }else{
            employeeRole = null;
        }
        addToExtent();
    }

    public Employee getEmployeeRole() {
        if(employeeRole == null){
            throw new IllegalArgumentException("you are not allowed to do it");
        }
        return employeeRole;
    }

    public Resident getResidentRole() {
        if(residentRole == null){
            throw new IllegalArgumentException("you are not allowed to do it");
        }
        return residentRole;
    }

    private void validateTypes(EnumSet<PersonType> types){
        if(types == null || types.isEmpty()){
            throw new IllegalArgumentException("types can't be null");
        }
        if(types.contains(null)){
            throw new IllegalArgumentException("types can't contains null");
        }
        if(types.size() > 2){
            throw new IllegalArgumentException("you can't have more 2 types");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id > 0){
            this.id = id;
        }else{
            throw new IllegalArgumentException("id must be a positive value");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("name is required");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname == null || surname.isBlank()){
            throw new IllegalArgumentException("surname is required");
        }
        this.surname = surname;
    }

    @Override
    public void removeFromExtent() {
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}