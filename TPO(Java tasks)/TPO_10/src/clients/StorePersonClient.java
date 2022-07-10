package clients;

import services.FilterRequest;
import services.IStorePerson;
import services.Person;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class StorePersonClient {
    public  List<Person> personList;

    public StorePersonClient(FilterRequest request) throws MalformedURLException {
        URL url = new URL("http://localhost:7779/ws/person?wsdl");
        QName qname = new QName("http://services/", "StorePersonImplService");
        Service service = Service.create(url, qname);
        IStorePerson person = service.getPort(IStorePerson.class);
        personList = person.filter(request);
    }
}