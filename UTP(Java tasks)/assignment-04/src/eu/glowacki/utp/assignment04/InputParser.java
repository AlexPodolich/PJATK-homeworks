package eu.glowacki.utp.assignment04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public final class InputParser {
	private static final String firstName = "(?<firstName>[A-Z][a-z]+)";
	private static final String secondName = "(?<secondName>[A-Z][a-z]+)";

	private static final String year = "(?<year>19[0-9][0-9]|20[0-9][0-9])";
	private static final String month = "(?<month>0[1-9]|1[012])";
	private static final String day = "(?<day>0[1-9]|[12][0-9]|3[01])";

	private static final String birthDate = "(?<birthDate>" + year + '-' + month + '-' + day + ")";

	private static final String person = firstName + "\\s+" + secondName + "\\s+" + birthDate;

	private static boolean checkFile(File file) {
		return file.exists() && file.isFile() && file.canRead();
	}

	public static List<Person> parse(File file) {
		if (!checkFile(file)){
			return emptyList();
		}

		List<Person> people = null;

		try(Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
			people = scanner.findAll(person).map(Person::new).collect(toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

//		System.out.println("Data from file:");
//		try(Stream<String> lines = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
//			lines.forEach(System.out::println);
//		} catch (IOException e){
//			e.printStackTrace();
//		}

		return people;

	}
}