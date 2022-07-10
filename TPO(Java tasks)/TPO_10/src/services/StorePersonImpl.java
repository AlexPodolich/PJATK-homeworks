package services;

import javax.jws.WebService;
import java.util.*;
import java.util.stream.Collectors;

@WebService(name = "IStorePerson")
public class StorePersonImpl implements IStorePerson {

    private final Map<String, List<Person>> _bySurname;
    private final Map<Date, List<Person>> _byBirthDate;


    public StorePersonImpl(List<Person> personList) {
        _bySurname = personList.stream().collect(Collectors.groupingBy(person -> person.getPersonSurname()));
        _byBirthDate = personList.stream().collect(Collectors.groupingBy(person -> person.getPersonBirthOfDate()));
    }

    @Override
    public List<Person> filter(FilterRequest request) {
        List<Person> bySurname = request.personSurname != null ? _bySurname.get(request.personSurname)
                : _bySurname.values().stream().flatMap(List::stream).collect(Collectors.toList());
        List<Person> byBirthDate = request.personBirthDate != null ? _byBirthDate.get(request.personBirthDate)
                : _byBirthDate.values().stream().flatMap(List::stream).collect(Collectors.toList());

        List<Person> result = bySurname.stream().distinct().filter(byBirthDate::contains).collect(Collectors.toList());
        return result;
    }
}