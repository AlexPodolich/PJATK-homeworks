package Overlapping;

import java.util.EnumSet;

public class Main {
    public static void main(String[] args) {
        Person person = new Person(EnumSet.of(PersonType.EMPLOYEE, PersonType.RESIDENT), 1, "Name1", "Surname1", "+380675329590", "adpodol@gmail.com", "+380675329590");
        System.out.println(person);
        System.out.println(person.getEmployeeRole());
        System.out.println(person.getResidentRole());

        Person person1 = new Person(EnumSet.of(PersonType.EMPLOYEE), 2, "Name2", "Surname2", "+380675329590", "adpodol@gmail.com", "+380675329590");

        System.out.println(person1);
        System.out.println(person1.getEmployeeRole());
        //System.out.println(person1.getResidentRole()); // Exception

        Person person2 = new Person(EnumSet.of(PersonType.RESIDENT), 3, "Name3", "Surname3", "+380675329590", "adpodol@gmail.com", "+380675329590");

        System.out.println(person2);
        //System.out.println(person2.getEmployeeRole()); // Exception
        System.out.println(person2.getResidentRole());
    }
}
