package Project;

import Project.Employee.Employee;

import java.util.*;
import java.util.stream.Collectors;

import Enum.PersonHealth;
import Enum.EmployeeType;
import Enum.PersonType;
import Project.Resident.Resident;

public class Person extends ObjectPlus{
    private final PersonHealth health;

    private Set<String> listOfDemands = new HashSet<>();
    private String medicalCard;
    private long id;
    private String name;
    private String surname;

    private final Resident residentRole;
    private final Employee employeeRole;
    private EmployeeType employeeType;

/*    private final Project.Employee.Administrator administratorRole;
    private final Project.Employee.Security securityRole;
    private final Project.Employee.CleaningStuff cleaningStuffRole;
    private final Project.Employee.RoomInspector roomInspectorRole;*/

    public Person(EnumSet<PersonType> types, // person types
                  EmployeeType employeeType,
                  PersonHealth health, String medicalCard, String demand, // multi-aspect
                  long id, String name, String surname, //person fields
                  String residentPhoneNumber, String residentEmail, // for resident
                  String workingPhoneNumber, // for Employee
                  String adminResponsibility, // for Administrator
                  String patrolArea,    // for Security
                  String cleaningArea,  // for Cleaning stuff
                  String inspectedRooms // for RoomInspector
                  ) {
        validateTypes(types);
        setId(id);
        setName(name);
        setSurname(surname);
        this.employeeType = employeeType;
//        this.employeeRole = employeeRole;
//        this.residentRole = residentRole;
//
//        this.administratorRole = administratorRole;
//        this.securityRole = securityRole;
//        this.cleaningStuffRole = cleaningStuffRole;
//        this.roomInspectorRole = roomInspectorRole;

        //check for null
        if(health == null){
            throw new IllegalArgumentException("health status is required");
        }
        this.health = health;

        //conditionally set health attributes

        if(health.equals(PersonHealth.HEALTHY)){
            setMedicalCard(medicalCard);
        }else {
            setMedicalCard(null);
        }

        if(health.equals(PersonHealth.DISABLED)){
            addDemand(demand);
        }else {
            listOfDemands = null;
        }

        if(types.contains(PersonType.RESIDENT)){
            if(residentEmail == null){
                residentRole = new Resident(this, residentPhoneNumber);
            }else{
                residentRole = new Resident(this, residentPhoneNumber, residentEmail);
            }
        }else{
            residentRole = null;
        }

        if(types.contains(PersonType.EMPLOYEE)){
            employeeRole = new Employee(this, workingPhoneNumber, employeeType, adminResponsibility, patrolArea, cleaningArea, inspectedRooms);
        }else{
            employeeRole = null;
        }



/*        if((types.contains(Enum.PersonType.ADMINISTRATOR) && (types.contains(Enum.PersonType.SECURITY) || types.contains(Enum.PersonType.CLEANING_STUFF) || types.contains(Enum.PersonType.ROOM_INSPECTOR))) ||
           (types.contains(Enum.PersonType.SECURITY) && (types.contains(Enum.PersonType.ADMINISTRATOR) || types.contains(Enum.PersonType.CLEANING_STUFF) || types.contains(Enum.PersonType.ROOM_INSPECTOR))) ||
           (types.contains(Enum.PersonType.CLEANING_STUFF) && (types.contains(Enum.PersonType.ADMINISTRATOR) || types.contains(Enum.PersonType.SECURITY) || types.contains(Enum.PersonType.ROOM_INSPECTOR))) ||
           (types.contains(Enum.PersonType.ROOM_INSPECTOR) && (types.contains(Enum.PersonType.ADMINISTRATOR) || types.contains(Enum.PersonType.CLEANING_STUFF) || types.contains(Enum.PersonType.SECURITY))))
        {
            throw new IllegalArgumentException("you can't have 2 employee roles");
        }*/

        /*if(types.contains(Enum.PersonType.SECURITY) && (types.contains(Enum.PersonType.ADMINISTRATOR) ||
                types.contains(Enum.PersonType.CLEANING_STUFF) || types.contains(Enum.PersonType.ROOM_INSPECTOR)))
        {
            throw new IllegalArgumentException("you can't have 2 employee roles");
        }

        if(types.contains(Enum.PersonType.CLEANING_STUFF) && (types.contains(Enum.PersonType.SECURITY) ||
                types.contains(Enum.PersonType.ADMINISTRATOR) || types.contains(Enum.PersonType.ROOM_INSPECTOR)))
        {
            throw new IllegalArgumentException("you can't have 2 employee roles");
        }

        if(types.contains(Enum.PersonType.ROOM_INSPECTOR) && (types.contains(Enum.PersonType.SECURITY) ||
                types.contains(Enum.PersonType.CLEANING_STUFF) || types.contains(Enum.PersonType.ADMINISTRATOR)))
        {
            throw new IllegalArgumentException("you can't have 2 employee roles");
        }*/

/*        if(types.contains(Enum.PersonType.ADMINISTRATOR)){
            administratorRole = new Project.Employee.Administrator(this, workingPhoneNumber, adminResponsibility);
        }else{
            administratorRole = null;
        }

        if(types.contains(Enum.PersonType.SECURITY)){
            securityRole = new Project.Employee.Security(this, workingPhoneNumber, patrolArea);
        }else{
            securityRole = null;
        }

        if(types.contains(Enum.PersonType.CLEANING_STUFF)){
            cleaningStuffRole = new Project.Employee.CleaningStuff(this, workingPhoneNumber, cleaningArea);
        }else{
            cleaningStuffRole = null;
        }

        if(types.contains(Enum.PersonType.ROOM_INSPECTOR)){
            roomInspectorRole = new Project.Employee.RoomInspector(this, workingPhoneNumber, inspectedRooms);
        }else{
            roomInspectorRole = null;
        }*/

    }

    public Employee getEmployeeRole() {
        if(employeeRole == null){
            throw new IllegalArgumentException("you are not allowed to do it");
        }
        return employeeRole;
    }

    public PersonHealth getHealth() {
        return health;
    }

    public Set<String> getListOfDemands() {
        return listOfDemands;
    }

    public void addDemand(String demand){
        if(demand == null || demand.isBlank()){
            throw new IllegalArgumentException("demand can't be null");
        }
        listOfDemands.add(demand);
    }

    public String getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(String medicalCard) {
        if(medicalCard == null || medicalCard.isBlank()){
            throw new IllegalArgumentException("medical Card can't be null");
        }
        this.medicalCard = medicalCard;
    }

    private void validateTypes(EnumSet<PersonType> types){
        if(types == null){
            throw new IllegalArgumentException("types can't be null");
        }
        if(types.contains(null)){
            throw new IllegalArgumentException("types can't contains null");
        }
        if(types.size() > 2){
            throw new IllegalArgumentException("you can't have more 2 types");
        }
    }

    public Resident getResidentRole() {
        return residentRole;
    }

/*    public Project.Employee.Administrator getAdministratorRole() {
        return administratorRole;
    }

    public Project.Employee.Security getSecurityRole() {
        return securityRole;
    }

    public Project.Employee.CleaningStuff getCleaningStuffRole() {
        return cleaningStuffRole;
    }

    public Project.Employee.RoomInspector getRoomInspectorRole() {
        return roomInspectorRole;
    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(getExtent(Person.class).stream()
                .filter(m -> m.id == id)
                .collect(Collectors.toList()).size() > 0){
            throw new IllegalArgumentException("id must be unique");
        }

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

    public void performDailyTask(){

    }

    @Override
    public String toString() {
        return "Project.Person{" +
                "health=" + health +
                ", listOfDemands=" + listOfDemands +
                ", medicalCard='" + medicalCard + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
/*                ", administratorRole=" + administratorRole +
                ", securityRole=" + securityRole +
                ", cleaningStuffRole=" + cleaningStuffRole +*/
                '}';
    }
}
