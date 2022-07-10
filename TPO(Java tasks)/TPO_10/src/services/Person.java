package services;

import java.util.Date;
import java.util.Objects;

public class Person {

    private String personName;
    private String personSurname;
    private Date personBirthOfDate;

    public Person(  ) {
    }

    public Person(String firstName, String surname, Date birthDate) {
        personName = firstName;
        personSurname = surname;
        personBirthOfDate = birthDate;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public Date getPersonBirthOfDate() {
        return personBirthOfDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personName, person.personName) && Objects.equals(personSurname, person.personSurname) && Objects.equals(personBirthOfDate, person.personBirthOfDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personName, personSurname, personBirthOfDate);
    }

    @Override
    public String toString() {
        return "Person{" +
                "personName='" + personName + '\'' +
                ", personSurname='" + personSurname + '\'' +
                ", personBirthOfDate=" + personBirthOfDate +
                '}';
    }
}
