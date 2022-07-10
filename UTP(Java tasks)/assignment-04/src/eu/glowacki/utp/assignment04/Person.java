package eu.glowacki.utp.assignment04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.regex.MatchResult;

public final class Person implements Comparable<Person> {
	
	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;
	
	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}

	public Person(MatchResult matchResult) {
		this(matchResult.group(1), matchResult.group(2), Date.from(LocalDate.parse(matchResult.group(3)).atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return Objects.equals(_firstName, person._firstName) && Objects.equals(_surname, person._surname) && Objects.equals(_birthdate, person._birthdate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(_firstName, _surname, _birthdate);
	}

	@Override
	public int compareTo(Person otherPerson) {
		int result = _surname.compareTo(otherPerson._surname);
		if (result != 0) return result;
		result = _firstName.compareTo(otherPerson._firstName);
		if (result != 0) return result;
		return _birthdate.compareTo(otherPerson._birthdate);
	}

	public String get_firstName() {
		return _firstName;
	}

	public Date get_birthdate() {
		return _birthdate;
	}

	public String get_surname() {
		return _surname;
	}

	@Override
	public String toString() {
		return get_firstName() + " " + get_surname() + " " + get_birthdate();
	}


	public void serialize(DataOutputStream outputStream) throws CustomException{
		try {
			outputStream.writeUTF(_firstName);
			outputStream.writeUTF(_surname);
			outputStream.writeLong(_birthdate.getTime());
		} catch (Throwable exception) {
			throw new CustomException("Error", exception);
		}
	}

	public static Person deserialize(DataInputStream inputStream) throws  CustomException{
		try{
			String firstName = inputStream.readUTF();
			String secondName = inputStream.readUTF();
			Date birthDate = new Date(inputStream.readLong());
			return new Person(firstName, secondName, birthDate);
		} catch(Throwable exception){
			throw new CustomException("Error", exception);
		}
	}
}