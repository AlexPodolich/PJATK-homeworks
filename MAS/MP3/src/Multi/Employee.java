package Multi;

import Project.ObjectPlus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Employee extends ObjectPlus {
    private long id;
    private String name;
    private String surname;
    private List<String> workingPhoneNumbers = new ArrayList<>();
    private static Pattern validPhoneRegex = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$", Pattern.CASE_INSENSITIVE);

    public Employee(long id, String name, String surname, String workingPhoneNumber) {
        setId(id);
        setName(name);
        setSurname(surname);
        addWorkingPhoneNumber(workingPhoneNumber);

        addToExtent();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(getExtent(Employee.class).stream()
                .filter(m -> m.id == id)
                .collect(Collectors.toList()).size() > 0){
            throw new IllegalArgumentException("id must be unique");
        }

        if (id > 0){
            this.id = id;
        }else{
            throw new IllegalArgumentException("id must be a positive value");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("name is required");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname == null || surname.isBlank()){
            throw new IllegalArgumentException("surname is required");
        }
        this.surname = surname;
    }

    public List<String> getWorkingPhoneNumbers() {
        return Collections.unmodifiableList(workingPhoneNumbers);
    }

    public void addWorkingPhoneNumber(String phoneNum) {
        if (phoneNum == null || phoneNum.isBlank()) {
            throw new IllegalArgumentException("phoneNum is required");
        }
        //phone validation
        if (!validPhoneRegex.matcher(phoneNum).matches()) {
            throw new IllegalArgumentException("You should provide proper phone number");
        }
        workingPhoneNumbers.add(phoneNum);
    }

    public void removeWorkingPhoneNumber(String phoneNum) {
        if (phoneNum == null || phoneNum.isBlank()) {
            throw new IllegalArgumentException("phoneNum is required");
        }
        if (!workingPhoneNumbers.contains(phoneNum)) {
            throw new IllegalArgumentException("phoneNum doesn't exist");
        }
        if (workingPhoneNumbers.size() == 1) {
            throw new IllegalArgumentException("you can not remove last working number");
        }
        workingPhoneNumbers.remove(phoneNum);
    }

    @Override
    public void removeFromExtent() {
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", workingPhoneNumbers=" + workingPhoneNumbers +
                '}';
    }
}