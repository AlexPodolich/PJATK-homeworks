package Project.Test;

public class Test {
    /*@org.junit.Project.Project.Test.Test
    public void testCreate() {
        Project.Project.Employee.Employee employee = new Project.Project.Employee.Employee(1, "Alex", "Marquizy", 1000.0f);
        Project.Project.Dormitory.Dormitory dormitory = new Project.Project.Dormitory.Dormitory(1, "Student", new Project.Project.Dormitory.Address("Suwak", 11, "Warsaw"), Project.Enum.DormitoryRating._4);
        Project.Project.Room room = new Project.Project.Room(dormitory,1, "B204", 333.0f, "Project.Project.Room desc", 30);
        Project.Project.Employment employment = new Project.Project.Employment(dormitory, employee, LocalDate.parse("2020-01-01"));
        Project.Project.Resident.Resident resident = new Project.Project.Resident.Resident(1, "Evangilion", "Bechkermek", "+380675329590", "evabech@gmail.com");
        Project.Project.ReservedRoom reservedRoom = new Project.Project.ReservedRoom(room, resident, LocalDate.parse("2020-01-01"), LocalDate.parse("2020-11-01"), 1000);
        Project.Project.Resident.Report report = new Project.Project.Resident.Report(1, "desc", "B204",resident);
        Project.Project.Employee.RoomInspector roomInspector = new Project.Project.Employee.RoomInspector("Inspector 333");

        Assert.assertNotNull(employee);
        Assert.assertNotNull(dormitory);
        Assert.assertNotNull(room);
        Assert.assertNotNull(employment);
        Assert.assertNotNull(resident);
        Assert.assertNotNull(reservedRoom);
        Assert.assertNotNull(report);
        Assert.assertNotNull(roomInspector);

        for(Map.Entry mp : Project.Project.ObjectPlus.getExtent().entrySet()){
            System.out.println(mp.getKey()+ " " +mp.getValue());
        }
    }

    @org.junit.Project.Project.Test.Test
    public void testConnections() {
        Project.Project.Employee.Employee employee = new Project.Project.Employee.Employee(1, "Alex", "Marquizy", 1000.0f);
        Project.Project.Dormitory.Dormitory dormitory = new Project.Project.Dormitory.Dormitory(1, "Student", new Project.Project.Dormitory.Address("Suwak", 11, "Warsaw"), Project.Enum.DormitoryRating._4);
        Project.Project.Room room = new Project.Project.Room(dormitory,1, "B204", 333.0f, "Project.Project.Room desc", 30);
        Project.Project.Employment employment = new Project.Project.Employment(dormitory, employee, LocalDate.parse("2020-01-01"));
        dormitory.addRoom(room);
        Project.Project.Resident.Resident resident = new Project.Project.Resident.Resident(1, "Evangilion", "Bechkermek", "+380675329590", "evabech@gmail.com");
        Project.Project.ReservedRoom reservedRoom = new Project.Project.ReservedRoom(room, resident, LocalDate.parse("2020-01-01"), LocalDate.parse("2020-11-01"), 1000);
        Project.Project.Resident.Report report = new Project.Project.Resident.Report(1, "desc", "B204",resident);
        Project.Project.Employee.RoomInspector roomInspector = new Project.Project.Employee.RoomInspector("Inspector 333");
        room.setRoomInspector(roomInspector);


        Assert.assertTrue(dormitory.getEmployments().contains(employment));
        Assert.assertTrue(employee.getEmployments().contains(employment));
        Assert.assertEquals(employment.getDormitory(), dormitory);
        Assert.assertEquals(employment.getEmployee(), employee);
        Assert.assertTrue(resident.getReservedRooms().contains(reservedRoom));
        Assert.assertTrue(room.getReservedRooms().contains(reservedRoom));
        Assert.assertEquals(reservedRoom.getRoom(), room);
        Assert.assertEquals(reservedRoom.getResident(), resident);
        Assert.assertTrue(resident.getReports().contains(report));
        Assert.assertEquals(report.getReporter(), resident);
        Assert.assertTrue(roomInspector.getRoomList().contains(room));
        Assert.assertEquals(room.getRoomInspector(),roomInspector);
        Assert.assertTrue(dormitory.getRooms().contains(room));
        Assert.assertEquals(room.getDormitory(), dormitory);

        for(Map.Entry mp : Project.Project.ObjectPlus.getExtent().entrySet()){
            System.out.println(mp.getKey()+ " " +mp.getValue());
        }
    }

    @org.junit.Rule
    public final ExpectedException exception = ExpectedException.none();
    @org.junit.Project.Project.Test.Test
    public void testConnectionsErrors() {
        Project.Project.Employee.Employee employee = new Project.Project.Employee.Employee(1, "Alex", "Marquizy", 1000.0f);
        Project.Project.Dormitory.Dormitory dormitory = new Project.Project.Dormitory.Dormitory(1, "Student", new Project.Project.Dormitory.Address("Suwak", 11, "Warsaw"), Project.Enum.DormitoryRating._4);
        Project.Project.Room room = new Project.Project.Room(dormitory,1, "B204", 333.0f, "Project.Project.Room desc", 30);
        Project.Project.Employment employment = new Project.Project.Employment(dormitory, employee, LocalDate.parse("2020-01-01"));
        Project.Project.Resident.Resident resident = new Project.Project.Resident.Resident(1, "Evangilion", "Bechkermek", "+380675329590", "evabech@gmail.com");
        Project.Project.ReservedRoom reservedRoom = new Project.Project.ReservedRoom(room, resident, LocalDate.parse("2020-01-01"), LocalDate.parse("2020-11-01"), 1000);
        Project.Project.Resident.Report report = new Project.Project.Resident.Report(1, "desc", "B204",resident);
        Project.Project.Employee.RoomInspector roomInspector = new Project.Project.Employee.RoomInspector("Inspector 333");

        exception.expect(IllegalArgumentException.class);
        dormitory.addRoom(null);
        room.addReservedRoom(null);
        resident.addReservedRoom(null);
        employee.addEmployment(null);
        dormitory.addEmployment(null);
        roomInspector.addRoom(null);
        room.setRoomInspector(null);
        resident.addReport(null);
        report.setReporter(null);
    }

    @org.junit.Project.Project.Test.Test
    public void testRemoveConnections() {
        Project.Project.Employee.Employee employee = new Project.Project.Employee.Employee(1, "Alex", "Marquizy", 1000.0f);
        Project.Project.Dormitory.Dormitory dormitory = new Project.Project.Dormitory.Dormitory(1, "Student", new Project.Project.Dormitory.Address("Suwak", 11, "Warsaw"), Project.Enum.DormitoryRating._4);
        Project.Project.Room room = new Project.Project.Room(dormitory,1, "B204", 333.0f, "Project.Project.Room desc", 30);
        Project.Project.Employment employment = new Project.Project.Employment(dormitory, employee, LocalDate.parse("2020-01-01"));
        Project.Project.Resident.Resident resident = new Project.Project.Resident.Resident(1, "Evangilion", "Bechkermek", "+380675329590", "evabech@gmail.com");
        Project.Project.ReservedRoom reservedRoom = new Project.Project.ReservedRoom(room, resident, LocalDate.parse("2020-01-01"), LocalDate.parse("2020-11-01"), 1000);
        Project.Project.Resident.Report report = new Project.Project.Resident.Report(1, "desc", "B204",resident);
        Project.Project.Employee.RoomInspector roomInspector = new Project.Project.Employee.RoomInspector("Inspector 333");
        dormitory.addRoom(room);
        room.setRoomInspector(roomInspector);

        dormitory.addRoom(room);
        //room.setRoomInspector(null);

        roomInspector.removeRoom(room);
        Assert.assertFalse(roomInspector.getRoomList().contains(room));
        Assert.assertFalse((Project.Project.ObjectPlus.getExtent(Project.Project.Employee.RoomInspector.class).get(0).getRoomList().contains(room)));
        Assert.assertNotEquals(room.getRoomInspector(), roomInspector);
        Assert.assertNotEquals(Project.Project.ObjectPlus.getExtent(Project.Project.Room.class).get(0).getRoomInspector(), roomInspector);
        Assert.assertNotEquals(Project.Project.ObjectPlus.getExtent(Project.Project.Room.class).get(0).getRoomInspector(), roomInspector);

        room.removeReservedRoom(reservedRoom);
        Assert.assertFalse(room.getReservedRooms().contains(reservedRoom));
        Assert.assertFalse(Project.Project.ObjectPlus.getExtent(Project.Project.Room.class).get(0).getReservedRooms().contains(reservedRoom));
        Assert.assertFalse(resident.getReservedRooms().contains(reservedRoom));
        Assert.assertFalse(Project.Project.ObjectPlus.getExtent(Project.Project.Resident.Resident.class).get(0).getReservedRooms().contains(reservedRoom));
        Assert.assertTrue(Project.Project.ObjectPlus.getExtent(Project.Project.ReservedRoom.class).size() == 0);

        dormitory.removeEmployment(employment);
        Assert.assertFalse(dormitory.getEmployments().contains(employment));
        Assert.assertFalse(Project.Project.ObjectPlus.getExtent(Project.Project.Dormitory.Dormitory.class).get(0).getEmployments().contains(employment));
        Assert.assertFalse(employee.getEmployments().contains(employment));
        Assert.assertFalse(Project.Project.ObjectPlus.getExtent(Project.Project.Employee.Employee.class).get(0).getEmployments().contains(employment));
        Assert.assertTrue(Project.Project.ObjectPlus.getExtent(Project.Project.Employment.class).size() == 0);

        resident.removeReport(report);
        Assert.assertFalse(resident.getReports().contains(report));
        Assert.assertFalse(Project.Project.ObjectPlus.getExtent(Project.Project.Resident.Resident.class).get(0).getReports().contains(report));
        Assert.assertNotEquals(report.getReporter(), resident);
        Assert.assertTrue(Project.Project.ObjectPlus.getExtent(Project.Project.Resident.Report.class).size() == 0);

        dormitory.removeRoom(room);
        Assert.assertFalse(dormitory.getRooms().contains(room));
        Assert.assertFalse(Project.Project.ObjectPlus.getExtent(Project.Project.Room.class).contains(room));
        Assert.assertNotEquals(reservedRoom.getRoom(), room);
        Assert.assertFalse(Project.Project.ObjectPlus.getExtent(Project.Project.ReservedRoom.class).contains(reservedRoom));
        Assert.assertFalse(roomInspector.getRoomList().contains(room));

        for(Map.Entry mp : Project.Project.ObjectPlus.getExtent().entrySet()){
            System.out.println(mp.getKey()+ " " +mp.getValue());
        }
    }

    @org.junit.Project.Project.Test.Test
    public void testRemoveConnectionsErrors() {
        Project.Project.Employee.Employee employee = new Project.Project.Employee.Employee(1, "Alex", "Marquizy", 1000.0f);
        Project.Project.Dormitory.Dormitory dormitory = new Project.Project.Dormitory.Dormitory(1, "Student", new Project.Project.Dormitory.Address("Suwak", 11, "Warsaw"), Project.Enum.DormitoryRating._4);
        Project.Project.Room room = new Project.Project.Room(dormitory,1, "B204", 333.0f, "Project.Project.Room desc", 30);
        Project.Project.Employment employment = new Project.Project.Employment(dormitory, employee, LocalDate.parse("2020-01-01"));
        Project.Project.Resident.Resident resident = new Project.Project.Resident.Resident(1, "Evangilion", "Bechkermek", "+380675329590", "evabech@gmail.com");
        Project.Project.ReservedRoom reservedRoom = new Project.Project.ReservedRoom(room, resident, LocalDate.parse("2020-01-01"), LocalDate.parse("2020-11-01"), 1000);
        Project.Project.Resident.Report report = new Project.Project.Resident.Report(1, "desc", "B204",resident);
        Project.Project.Employee.RoomInspector roomInspector = new Project.Project.Employee.RoomInspector("Inspector 333");
        dormitory.addRoom(room);
        room.setRoomInspector(roomInspector);

        exception.expect(IllegalArgumentException.class);
        dormitory.removeRoom(null);
        room.removeReservedRoom(null);
        resident.removeReport(null);
        employee.removeEmployment(null);
        dormitory.removeEmployment(null);
        roomInspector.removeRoom(null);
        room.removeReservedRoom(null);
        resident.removeReservedRoom(null);
    }*/
}
