package eu.glowacki.utp.assignment04.test;

import eu.glowacki.utp.assignment04.InputParser;
import eu.glowacki.utp.assignment04.Person;
import eu.glowacki.utp.assignment04.PersonDatabase;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public final class InputParserTest {
    File file = new File("resources/peopleList.txt");

    @Test
    public void parse() {
        assertTrue(file.exists());
        List<Person> result = InputParser.parse(file);
        assertNotNull(result);
        assertEquals(13, result.size());
    }
}