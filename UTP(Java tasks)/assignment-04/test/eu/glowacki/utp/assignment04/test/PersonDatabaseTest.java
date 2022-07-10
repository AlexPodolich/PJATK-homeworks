package eu.glowacki.utp.assignment04.test;

import eu.glowacki.utp.assignment04.Person;
import eu.glowacki.utp.assignment04.PersonDatabase;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonDatabaseTest {

    PersonDatabase personDatabase = new PersonDatabase(new File("resources/peopleList.txt"));

    public void checkPersons(Person expected, Person original) {
        assertEquals(expected.get_firstName(), original.get_firstName());
        assertEquals(expected.get_surname(), original.get_surname());
        assertEquals(expected.get_birthdate(), original.get_birthdate());
    }

    @Test
    public void sortedByFirstName() {
        List<Person> personList = personDatabase.sortedByFirstName();
        assertNotNull(personList);

        Person expectedPerson = new Person("Akame","Cola", Date.from(LocalDate.parse("2013-02-13").atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Person resultPerson = personList.get(0);
        checkPersons(expectedPerson, resultPerson);
    }

    @Test
    public void sortedByLastNameFirstNameAndBirthDate() {
        List<Person> personList = personDatabase.sortedBySurnameFirstNameAndBirthdate();
        assertNotNull(personList);

        Person expectedPerson = new Person("Levi","Akerman", Date.from(LocalDate.parse("1989-12-18").atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Person resultPerson = personList.get(0);
        checkPersons(expectedPerson, resultPerson);
    }

    @Test
    public void sortedByBirthDate() {
        List<Person> personList = personDatabase.sortedByBirthdate();
        assertNotNull(personList);

        Person expectedPerson = new Person("Mikasa","Akerman", Date.from(LocalDate.parse("1980-06-07").atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Person resultPerson = personList.get(0);
        checkPersons(expectedPerson, resultPerson);
    }

    @Test
    public void bornOnDay() {
        List<Person> personList = personDatabase.bornOnDay(Date.from(LocalDate.parse("2002-10-29").atStartOfDay(ZoneId.systemDefault()).toInstant()));
        assertNotNull(personList);

        assertEquals(4, personList.size());
    }

}