package junitTest;

import services.Person;
import clients.StorePersonClient;
import services.StorePersonPublisher;
import org.junit.Assert;
import services.FilterRequest;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.jupiter.api.Test;

public class test {
    public List<Person> list;
    public StorePersonPublisher publisher;
    public SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public test() throws ParseException {
        this.list = new ArrayList<>();
        list.add(new Person("Alex", "Marquizy", format.parse("29/10/2002")));
        list.add(new Person("Vasya", "Mozolenko", format.parse("15/08/2001")));
        list.add(new Person("Alex", "Podolich", format.parse("09/02/1999")));
        list.add(new Person("Mark", "Klim", format.parse("10/10/2005")));
        list.add(new Person("Anton", "Zhilkin", format.parse("29/10/2002")));
        list.add(new Person("Zheka", "Buryat", format.parse("14/01/1993")));
        list.add(new Person("Masha", "Podolich", format.parse("20/08/1995")));
        list.add(new Person("Kiril", "Poka", format.parse("03/03/2003")));
        list.add(new Person("Ivan", "Zhilkin", format.parse("19/03/2008")));
        publisher = new StorePersonPublisher();
        publisher.run(list);
    }

    @Test
    public void filter() throws MalformedURLException, ParseException {
        FilterRequest request = new FilterRequest("Zhilkin", null);
        StorePersonClient client = new StorePersonClient(request);
        List<Person> myList = client.personList;
        Assert.assertNotNull(myList);
        Assert.assertEquals(myList.size(), 2);
        Assert.assertEquals(myList.get(0).getPersonSurname(), "Zhilkin");
        Assert.assertEquals(myList.get(1).getPersonSurname(), "Zhilkin");


        FilterRequest request1 = new FilterRequest(null, format.parse("29/10/2002"));
        StorePersonClient client1 = new StorePersonClient(request1);
        List<Person> myList1 = client1.personList;
        Assert.assertNotNull(myList1);
        Assert.assertEquals(myList1.size(), 2);
    }
}
