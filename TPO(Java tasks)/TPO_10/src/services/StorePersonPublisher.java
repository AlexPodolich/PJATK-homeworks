package services;

import javax.xml.ws.Endpoint;
import java.util.List;

public class StorePersonPublisher {
    public void run(List<Person> persons){
        Endpoint.publish("http://localhost:7779/ws/person", new StorePersonImpl(persons));
    }
}