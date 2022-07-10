package eu.glowacki.utp.assignment04.test;

import eu.glowacki.utp.assignment04.CustomException;
import eu.glowacki.utp.assignment04.InputParser;
import eu.glowacki.utp.assignment04.Person;
import eu.glowacki.utp.assignment04.PersonDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static eu.glowacki.utp.assignment04.InputParser.parse;

public class Assignment08Test {
    private static final String PEOPLEPATH = "resources/allPeople.txt";
    private static final String PATH = "resources/peopleList2.txt";
    private static final String PATH2 = "resources/peopleList.txt";
    private static Person PERSON = new Person("Alex", "Podolich", parseDate("2002-10-29"));

    @Test
    public void assignment8PersonTest() throws Throwable{
        Person person = null;
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(PATH));
             DataInputStream in = new DataInputStream(new FileInputStream(PATH))) {
            PERSON.serialize(out);
            person = Person.deserialize(in);
        } catch (CustomException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(PERSON, person);
        System.out.println(person);
    }

    @Test
    public void assignment8DatabaseTest() throws Throwable{
        List<Person> personListParsed = parse(new File(PEOPLEPATH));
        PersonDatabase personDatabase = new PersonDatabase(personListParsed);
        PersonDatabase personDatabaseNew = null;

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(PATH2));
             DataInputStream in = new DataInputStream(new FileInputStream(PATH2))) {
            personDatabase.serialize(out);
            personDatabaseNew = PersonDatabase.deserialize(in);
        } catch (CustomException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < personDatabase.size(); i++) {
            Assert.assertEquals(personDatabase.getPeople().get(i), personDatabaseNew.getPeople().get(i));
            System.out.println(personDatabase.getPeople().get(i));
        }

    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
