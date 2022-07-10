package eu.glowacki.utp.assignment04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;

public final class PersonDatabase {

	private List<Person> people;
	private Map<Date, List<Person>> mappedPeople;


	public PersonDatabase(File file) {
		people = InputParser.parse(file);
		mappedPeople = people.stream()
				.collect(groupingBy(Person::get_birthdate));
		System.out.println();
	}

	public PersonDatabase(List<Person> listPeople) {
		people = listPeople;
		mappedPeople = people.stream()
				.collect(groupingBy(Person::get_birthdate));
	}

	public List<Person> sortedByFirstName() {
		people.sort(Comparator.comparing(Person::get_firstName));

		System.out.println("Sorted data by first name:");
		people.forEach(System.out::println);
		System.out.println();

		return people;
	}
	
	public List<Person> sortedBySurnameFirstNameAndBirthdate() {
		people.sort(Person::compareTo);

		System.out.println("Sorted data by all parameters:");
		people.forEach(System.out::println);
		System.out.println();

		return people;
	}
	
	public List<Person> sortedByBirthdate() {
		people.sort(Comparator.comparing(Person::get_birthdate));

		System.out.println("Sorted data by birth date:");
		people.forEach(System.out::println);
		System.out.println();

		return people;
	}

	public int size(){
		return people.size();
	}
	
	public List<Person> bornOnDay(Date date) {
		System.out.println("People with same date of birth:");
		mappedPeople.get(date).forEach(System.out::println);
		System.out.println();

		return mappedPeople.get(date);
	}

	public void serialize(DataOutputStream outputStream) throws CustomException {
		try {
			outputStream.writeInt(people.size());
			for (Person person : people)
				person.serialize(outputStream);
		} catch (Throwable exception) {
			throw new CustomException("Error", exception);
		}
	}

	public static PersonDatabase deserialize(DataInputStream inputStream) throws CustomException {
		try {
			List<Person> peopleList = new ArrayList<>();
			int size = inputStream.readInt();
			for (int i = 0; i < size; ++i)
				peopleList.add(Person.deserialize(inputStream));
			return new PersonDatabase(peopleList);
		} catch (Throwable exception) {
			throw new CustomException("Error", exception);
		}

	}

	@Override
	public String toString() {
		return "PersonDatabase{" +
				"people=" + people +
				", mappedPeople=" + mappedPeople +
				'}';
	}

	public List<Person> getPeople() {
		return people;
	}

	public Map<Date, List<Person>> getMappedPeople() {
		return mappedPeople;
	}
}