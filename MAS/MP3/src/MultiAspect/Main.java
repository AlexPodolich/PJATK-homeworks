package MultiAspect;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee(PersonHealth.HEALTHY, 1, "String name", "String surname", "String medicalCard", "String demand", "+380675329590");

        System.out.println(employee);
        //System.out.println(employee.getListOfDemands()); // Exception
        System.out.println(employee.getMedicalCard());

        Resident resident = new Resident(PersonHealth.DISABLED, 1, "String name", "String surname", "String medicalCard", "String demand", "+380675329590", "adpodol@gmail.com");

        System.out.println(resident);
        System.out.println(resident.getListOfDemands());
        //System.out.println(resident.getMedicalCard()); // Exception

        resident.addDemand("newDemand");
        //employee.addDemand("newDemand"); // Exception

        //resident.setMedicalCard("medCard"); // Exception
        employee.setMedicalCard("medCard");
    }
}
