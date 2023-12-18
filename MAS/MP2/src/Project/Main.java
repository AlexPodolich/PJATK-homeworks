package Project;

import Project.Dormitory.Address;
import Project.Dormitory.Dormitory;
import Project.Employee.Employee;
import Project.Employee.Security;

import java.io.IOException;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Map;

import Enum.PersonType;
import Enum.EmployeeType;
import Enum.PersonHealth;
import Enum.DormitoryRating;
import Project.Resident.Document;
import Project.Resident.ResidencePermit;
import Project.Resident.SectionClub;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
/*        EnumSet<Enum.PersonType> enumSet = EnumSet.of(Enum.PersonType.ADMINISTRATOR);

        Project.Employee.Employee person = new Project.Person(enumSet, Enum.PersonHealth.HEALTHY, "medicalcard", "demand", 1,
                "name1", "Surname1", "+380675329590", "resEmail@gmail.com", "+380675329590", "adminRespons",
                "patrolArea", "cleaningArea", "InspectedRooms").getAdministratorRole();*/

/*        System.out.println(person);
        System.out.println("-----------");

        person = person.getOwner().getAdministratorRole().becomeSecurity("patrolArea");
        System.out.println(person);

        person = person.getOwner().getSecurityRole().becomeCleaningStuff("cleaningArea");
        System.out.println(person);

        person = person.getOwner().getCleaningStuffRole().becomeAdministrator("responsibility1");
        System.out.println(person);*/




        EnumSet<PersonType> enumSet = EnumSet.of(PersonType.RESIDENT);

        Person resident = new Person(enumSet, EmployeeType.ADMINISTRATOR, PersonHealth.HEALTHY, "medicalcard", "demand", 1,
                "name1", "Surname1", "+380675329590", "resEmail@gmail.com", "+380675329590", "adminRespons",
                "patrolArea", "cleaningArea", "InspectedRoom");
        Person resident2 = new Person(enumSet, EmployeeType.ADMINISTRATOR, PersonHealth.HEALTHY, "medicalcard", "demand", 1,
                "name2", "Surname2", "+380675329591", "resEmail2@gmail.com", "+380675329590", "adminRespons",
                "patrolArea", "cleaningArea", "InspectedRoom");
        Person resident3= new Person(enumSet, EmployeeType.ADMINISTRATOR, PersonHealth.HEALTHY, "medicalcard", "demand", 1,
                "name3", "Surname3", "+380675329592", "resEmail3@gmail.com", "+380675329590", "adminRespons",
                "patrolArea", "cleaningArea", "InspectedRoom");

        System.out.println(resident.getResidentRole());
        System.out.println("-----------");

        // For an attribute(static)
        //person.getResidentRole().setResidentPhoneNumber("error"); //exception You should provide proper phone number
        resident.getResidentRole().setResidentPhoneNumber("+380675329591");

        // For an attribute(dynamic)
        Dormitory dormitory = new Dormitory(1, "Student", new Address("Suwak", 11, "Warsaw"), DormitoryRating._4);
        EnumSet<PersonType> enumSet1 = EnumSet.of(PersonType.EMPLOYEE);
        Person employee = new Person(enumSet1, EmployeeType.ADMINISTRATOR, PersonHealth.HEALTHY, "medicalcard", "demand", 1,
                "name1", "Surname1", "+380675329590", "admin@gmail.com", "+380675329590", "adminRespons",
                "patrolArea", "cleaningArea", "InspectedRoom");

        Employment employment = new Employment(dormitory, employee.getEmployeeRole(), 1000.0f ,LocalDate.parse("2020-01-01"));
        System.out.println("MonthlySalary: " + employment.getMonthlySalary());
        //employment.setMonthlySalary(1251.0f); // exception you cannot increase monthly salary by 25% or more
        employment.setMonthlySalary(1250.0f);
        System.out.println("MonthlySalary: " + employment.getMonthlySalary());

        //employment.setMonthlySalary(1000f); // exception you cannot reduce monthly salary by 10% or more
        employment.setMonthlySalary(1200f);
        System.out.println("MonthlySalary: " + employment.getMonthlySalary());

        System.out.println("-----------");
        // UNIQUE
        Person person1 = new Person(enumSet, EmployeeType.ADMINISTRATOR, PersonHealth.HEALTHY, "medicalcard", "demand", 1, "name1", "Surname1", "+380675329590",
                "resEmail1@gmail.com", // exception residentEmail must be unique
                "+380675329590", "adminRespons", "patrolArea", "cleaningArea", "InspectedRoom");

        System.out.println(person1.getResidentRole());
        System.out.println("-----------");

        //ordered

        Room room1 = new Room(dormitory,1, "D301", 1363.0f, "Room desc", 30);
        Room room2 = new Room(dormitory,2, "C1", 8353.0f, "Room desc", 30);
        Room room3 = new Room(dormitory,3, "B2282", 2313.0f, "Room desc", 30);

        System.out.println(dormitory.getRooms());
        System.out.println("-----------");

        //bag/history
        ReservedRoom reservedRoom = new ReservedRoom(room1, resident.getResidentRole(), LocalDate.parse("2020-01-01"), LocalDate.parse("2020-11-01"), 1000);
        ReservedRoom reservedRoom2 = new ReservedRoom(room1, resident.getResidentRole(), LocalDate.parse("2021-01-01"), LocalDate.parse("2022-11-01"), 1000);

        System.out.println(reservedRoom);
        System.out.println("-----------");
        System.out.println(reservedRoom2);
        System.out.println("-----------");

        //xor
        Document document1 = new Document(1, "doc1", "content1");
        Document document2 = new Document(2, "doc2", "content2");
        Document document3 = new Document(3, "doc3", "content3");
        Document document4 = new Document(4, "doc4", "content4");
        resident.getResidentRole().addDocument(document1);
        resident.getResidentRole().addDocument(document2);
        resident.getResidentRole().addDocument(document3);
        resident.getResidentRole().addDocument(document4);

        System.out.println(resident.getResidentRole().getDocuments());
        System.out.println("-----------");

        ResidencePermit residencePermit = new ResidencePermit(1, "work", "active");
        //resident.getResidentRole().setResidencePermit(residencePermit); // you can't set residence permit, when you have documents

        resident.getResidentRole().removeDocument(document1);
        resident.getResidentRole().removeDocument(document2);
        resident.getResidentRole().removeDocument(document3);
        resident.getResidentRole().removeDocument(document4);

        System.out.println(resident.getResidentRole().getDocuments());
        System.out.println("-----------");

        resident.getResidentRole().setResidencePermit(residencePermit);

        System.out.println(resident.getResidentRole().getResidencePermit());
        System.out.println("-----------");

        //resident.getResidentRole().addDocument(document1); // you can't add document, when you have residence permit

        resident.getResidentRole().removeResidencePermit();

        System.out.println(resident.getResidentRole().getResidencePermit());
        System.out.println("-----------");

        resident.getResidentRole().addDocument(document1);

        System.out.println(resident.getResidentRole().getDocuments());
        System.out.println("-----------");

        //subset
        SectionClub sectionClub = new SectionClub(1, "Football", 2);
        //sectionClub.addRegulatedBy(resident.getResidentRole()); // exception Club must have this resident as member to add him as regulator
        //resident.getResidentRole().addRegulates(sectionClub); // exception resident must be member of a section club
        sectionClub.addHasMembers(resident.getResidentRole());
        resident.getResidentRole().addRegulates(sectionClub);
        System.out.println("Consist of " + sectionClub.getConsistOf());
        System.out.println("Member of " + resident.getResidentRole().getMemberOf());
        System.out.println("Regulated by " + sectionClub.getRegulatedBy());
        System.out.println("Regulates " + resident.getResidentRole().getRegulates());

        resident2.getResidentRole().addMemberOf(sectionClub);
        //resident3.getResidentRole().addMemberOf(sectionClub); // exception sectionClub is full
        //sectionClub.addHasMembers(resident3.getResidentRole()); // exception sectionClub is full
        System.out.println("Consist of " + sectionClub.getConsistOf());
        System.out.println("Member of " + resident.getResidentRole().getMemberOf());
        System.out.println("Regulated by " + sectionClub.getRegulatedBy());
        System.out.println("Regulates " + resident.getResidentRole().getRegulates());
        System.out.println("-----------");


        //custom business constraint
        //ReservedRoom reservedRoom3 = new ReservedRoom(room1, resident.getResidentRole(), LocalDate.parse("2020-01-01"), LocalDate.parse("2020-02-01"), 1000); //exception can't reserve room to period < 3 months
        ReservedRoom reservedRoom3 = new ReservedRoom(room1, resident.getResidentRole(), LocalDate.parse("2020-01-01"), LocalDate.parse("2020-05-01"), 1000);


        System.out.println(reservedRoom3);
        System.out.println("-----------");



//        Dormitory dormitory = new Dormitory(1, "Student", new Address("Suwak", 11, "Warsaw"), DormitoryRating._4);
//        Room room = new Room(dormitory,1, "B204", 333.0f, "Room desc", 30);
//        room.setRoomInspector(roomInspector);
//
//        System.out.println("-----------");
//        System.out.println(room.getRoomInspector());



//        System.out.println("-----------");
//        for(Map.Entry mp : ObjectPlus.getExtent().entrySet()){
//            System.out.println(mp.getKey()+ " " +mp.getValue());
//        }
        /*Project.Project.Employee.Employee employee = new Project.Project.Employee.Employee(1, "Alex", "Marquizy", 1000.0f);
        Project.Project.Dormitory.Dormitory dormitory = new Project.Project.Dormitory.Dormitory(1, "Student", new Project.Project.Dormitory.Address("Suwak", 11, "Warsaw"), Project.Enum.DormitoryRating._4);
        //Project.Project.Dormitory.Dormitory dormitory2 = new Project.Project.Dormitory.Dormitory(2, "Student2", new Project.Project.Dormitory.Address("Suwak", 14, "Warsaw"), Project.Enum.DormitoryRating._4);
        //dormitory.createRoom(1, "B204", 333.0f, "Project.Project.Room desc", 30);
        Project.Project.Room room = new Project.Project.Room(dormitory,1, "B204", 333.0f, "Project.Project.Room desc", 30);
        Project.Project.Employment employment = new Project.Project.Employment(dormitory, employee, LocalDate.parse("2020-01-01"));


        //Project.Project.Employment employment2 = new Project.Project.Employment(dormitory2, employee, LocalDate.parse("2020-01-01"));

        //error Project.Project.Employment is already exists
        //Project.Project.Employment employment1 = new Project.Project.Employment(dormitory, employee, LocalDate.parse("2020-01-01"));

        dormitory.addRoom(room);
        Project.Project.Resident.Resident resident = new Project.Project.Resident.Resident(1, "Evangilion", "Bechkermek", "+380675329590", "evabech@gmail.com");
        Project.Project.Resident.Resident resident2 = new Project.Project.Resident.Resident(2, "Sasha", "Masher", "+380675329590", "evabech@gmail.com");

        Project.Project.ReservedRoom reservedRoom = new Project.Project.ReservedRoom(room, resident, LocalDate.parse("2020-01-01"), LocalDate.parse("2020-11-01"), 1000);
        Project.Project.Resident.Report report = new Project.Project.Resident.Report(1, "desc", "B204",resident);
        Project.Project.Resident.Report report2 = new Project.Project.Resident.Report(2, "desc2", "B204",resident2);

        //resident.addReport(report2);
        resident.removeReport(report);
        //remove report
        //report.setReporter(null);

        Project.Project.Employee.RoomInspector roomInspector = new Project.Project.Employee.RoomInspector("Inspector 333");
        //room.setRoomInspector(roomInspector);
        System.out.println(roomInspector.getRoomList());
        roomInspector.addRoom(room);

        //room.setRoomInspector(null);
        //error reservedRoom is already exists
        //Project.Project.ReservedRoom reservedRoom1 = new Project.Project.ReservedRoom(room, resident, LocalDate.parse("2020-01-01"), LocalDate.parse("2020-11-01"), 1000);

        //error end Date should be after start date
        //Project.Project.ReservedRoom reservedRoom = new Project.Project.ReservedRoom(room, resident, LocalDate.parse("2021-01-01"), LocalDate.parse("2020-11-01"), 1000);

//        Project.Project.ExtentHelper.saveAllClassExtents();
//        Project.Project.ExtentHelper.loadAllClassExtents();

        for(Map.Entry mp : Project.Project.ObjectPlus.getExtent().entrySet()){
            System.out.println(mp.getKey()+ " " +mp.getValue());
        }

        System.out.println();
        //room.setRoomInspector(null);
        roomInspector.removeRoom(room);
        System.out.println("ROOM INSPECTOR: " + Project.Project.ObjectPlus.getExtent(Project.Project.Room.class).get(0).toString());
        System.out.println("ROOM INSPECTOR: " + Project.Project.ObjectPlus.getExtent(Project.Project.Room.class).get(0).getRoomInspector());
        //dormitory.removeRoom(room);
        //room.removeFromExtent();

        System.out.println(dormitory);

        //returns null because room cannot exist without dorm
        System.out.println(room);

        System.out.println();


        //dormitory.createRoom(1, "B505", 5555.0f, "Project.Project.Room desc2", 45);

//        System.out.println(dormitory);
//        System.out.println(dormitory.getRooms().stream().filter(m -> m.getId() == 1).collect(Collectors.toList()));

//        dormitory.removeEmployment(employment);
//        employee.removeEmployment(employment);
//
//        System.out.println();
//        System.out.println(dormitory.toString());
//        System.out.println(employee.toString());
//        System.out.println(employment.toString());
//        System.out.println();
//
//        room.removeReservedRoom(reservedRoom);
//
//        System.out.println();
//        System.out.println(room.toString());
//        System.out.println(resident.toString());
//        System.out.println(reservedRoom.toString());
//        System.out.println();
        //dormitory.removeFromExtent();
        //report.removeFromExtent();
        //resident.removeFromExtent();
        //reservedRoom.remove();
        //room.removeFromExtent();
        //roomInspector.removeFromExtent();
        //employment.remove();
        //employee.removeFromExtent();

        for(Map.Entry mp : Project.Project.ObjectPlus.getExtent().entrySet()){
            System.out.println(mp.getKey()+ " " +mp.getValue());
        }

        System.out.println(Project.Project.ObjectPlus.extent.entrySet());
        Project.Project.ExtentHelper.saveAllClassExtents();
        //Project.Project.ExtentHelper.loadAllClassExtents();*/
    }

}