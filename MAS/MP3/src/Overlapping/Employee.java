package Overlapping;

import Project.ObjectPlus;


import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Employee extends ObjectPlus {
    private Person owner;
    private List<String> workingPhoneNumbers = new ArrayList<>();
    private static Pattern validPhoneRegex = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$", Pattern.CASE_INSENSITIVE);

    public Employee(Person owner, String workingPhoneNumber) {
        setOwner(owner);
        addWorkingPhoneNumber(workingPhoneNumber);
    }

    public Person getOwner() {
        return owner;
    }

    private void setOwner(Person owner) {
        if (owner == null) {
            throw new IllegalArgumentException("owner is required");
        }
        this.owner = owner;
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
                "owner=" + owner +
                ", workingPhoneNumbers=" + workingPhoneNumbers +
                '}';
    }
}