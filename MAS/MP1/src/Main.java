import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Employee employee = new Employee('1', "Alex", "Marquizy", 1000.0f, "+380675329590");
//        Room room = new Room('1', "B204", 333.0f, "Room desc", 35);
//        Dormitory dormitory = new Dormitory('2', "Student2", new Address("Suwak", 14, "Warsaw"), DormitoryRating._4);
//        dormitory.addEmp(employee);
//        dormitory.addRoom(room);
//        Resident resident = new Resident('1', "Evangilion", "Bechkermek", "+380675329590", "evabech@gmail.com");
//
//        resident.addRoom(room);
//        System.out.println(resident.getRooms());
//        System.out.println(room.getResidents());
//
//        try{
//            ExtentHelper.saveAllClassExtents();
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        ExtentHelper.loadAllClassExtents();
//
//        for(Map.Entry mp : ObjectPlus.getExtent().entrySet()){
//            System.out.println(mp.getKey()+ " " + mp.getValue());
//        }
//
//        System.out.println(" ");
//        employee.addWorkingPhoneNumber("+380675328180");
//        dormitory.removeRoom(room);
//        dormitory.removeEmp(employee);
//        System.out.println(dormitory);
//
//        System.out.println(" ");
//
//        System.out.println("Employees with name Alex: " + Employee.findByName("Alex"));
//
//        employee.addPenalty("Being Late 29.03.2022");
//        System.out.println("Actual salary for " + employee.getName() + ": " + employee.getActualSalary());
        employeeTest();
        System.out.println("-----------------");
        residentTest();
        System.out.println("-----------------");
        roomTest();
        System.out.println("-----------------");
        dormTest();
        try{
            ExtentHelper.saveAllClassExtents();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        ExtentHelper.loadAllClassExtents();
    }

    public static void employeeTest(){
        //constructors
        Employee employee = new Employee(1, "Alex", "Marquizy", 1000.0f, "+380675329590");
        Employee employee1 = new Employee(2, "Vasya", "Pupkin", 1200.0f, "730564606");

        //get methods
        System.out.println(employee.getId());
        System.out.println(employee.getName());
        System.out.println(employee.getSurname());
        System.out.println(employee.getMonthlySalary());
        System.out.println(employee.getWorkingPhoneNumbers());

        //multy value attribute add values
        employee.addPenalty("Being Late 29.03.2022");
        employee.addWorkingPhoneNumber("+380675329590");

        //derived attribute
        System.out.println("Actual salary for employee " + employee.getName() + " " + employee.getSurname() + ": " + employee.getActualSalary());

        //class method
        System.out.println("Employees with name Alex: " + Employee.findByName("Alex"));

        //call class method with illegal attribute
//        System.out.println("Employees with name Alex: " + Employee.findByName(""));
//        System.out.println("Employees with name Alex: " + Employee.findByName(null));

        //remove value from multy value attribute
        employee.removeWorkingPhoneNumber("+380675329590");
        System.out.println(employee.getWorkingPhoneNumbers());

        //set methods
        employee.setId(3);
        employee.setName("Kirich");
        employee.setSurname("Mirych");
        employee.setMonthlySalary(1450);

        System.out.println(employee.toString());

        //try to set illegal arguments
//        employee.setId(0);
//        employee.setName("");
//        employee.setSurname("");
//        employee.setMonthlySalary(-120);

        //try to remove last element from multy value attribute
//        employee.removeWorkingPhoneNumber("+380675328180");

        //try to remove empty multy value attribute
//        employee.removeWorkingPhoneNumber("");

        //try to remove multy value attribute that doesn't exist
//        employee.removeWorkingPhoneNumber("+123123");

    }

    public static void residentTest(){
        //constructors
        Resident resident = new Resident(1, "Evangilion", "Bechkermek", "0675329590", "evabech@gmail.com");

        //overloaded constuctor
        Resident resident1 = new Resident(2, "Alex", "Podolich", "0675328180");

        //get methods
        System.out.println(resident.getId());
        System.out.println(resident.getResidentName());
        System.out.println(resident.getResidentSurname());
        System.out.println(resident.getResidentPhoneNumber());
        System.out.println(resident.getResidentEmail());

        resident1.setResidentEmail(null);

        System.out.println(resident);

        //set methods
        resident.setId(3);
        resident.setResidentName("Kirich");
        resident.setResidentSurname("Mirych");
        resident.setResidentPhoneNumber("0675329690");
        resident.setResidentEmail("adpodol@gmail.com");

        System.out.println(resident);

        //try to set illegal arguments
//        resident.setId(0);
//        resident.setResidentName("");
//        resident.setResidentSurname("");
//        resident.setResidentPhoneNumber("1234");
//        resident.setResidentEmail("lolemail");

    }

    public static void roomTest(){
        //constructor
        Room room = new Room(1, "B204", 333.0f, "Room desc", 25);
        Room room1 = new Room(2, "B205", 355.0f, "Room desc2", 35);
        //get methods
        System.out.println(room.getId());
        System.out.println(room.getRoomNumber());
        System.out.println(room.getRoomPrice());
        System.out.println(room.getRoomDescription());
        System.out.println(room.getRoomSize());

        System.out.println(Room.getMinRoomSize());

        System.out.println(room);

        //set methods
        room.setId(3);
        room.setRoomNumber("A205");
        room.setRoomPrice(450);
        room.setRoomDescription("new room desc");
        room.setRoomSize(35);

        Room.setMinRoomSize(35);

        System.out.println(room);

        //try to set illegal arguments
//        room.setId(0);
//        room.setRoomNumber("");
//        room.setRoomPrice(0);
//        room.setRoomDescription("");
//        room.setRoomSize(15);

        //try to create room with room size < min room size
//        Room room1 = new Room('2', "B2041", 133.0f, "Room desc1", 15);
    }

    public static void dormTest(){
        //constructor
        Dormitory dormitory = new Dormitory(1, "Student", new Address("Suwak", 14, "Warsaw"), DormitoryRating._4);

        //get methods
        System.out.println(dormitory.getId());
        System.out.println(dormitory.getName());
        System.out.println(dormitory.getAddress());
        System.out.println(dormitory.getRating());

        System.out.println(dormitory);

        //set methods
        dormitory.setId(2);
        dormitory.setName("Dorm13");
        dormitory.setAddress(new Address("Street", 12, "Warsaw"));
        dormitory.setRating(DormitoryRating._1);

        System.out.println(dormitory);

        //try to set illegal arguments
//        dormitory.setId(0);
//        dormitory.setName("");
//        dormitory.setAddress(new Address("", 0, ""));
//        dormitory.setRating(null);
    }



}