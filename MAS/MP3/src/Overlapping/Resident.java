package Overlapping;

import Project.ObjectPlus;

import java.util.*;
import java.util.regex.Pattern;

public class Resident extends ObjectPlus {
    private Person owner;
    private String residentPhoneNumber;
    private String residentEmail;
    private static Pattern validEmailRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static Pattern validPhoneRegex = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$", Pattern.CASE_INSENSITIVE);

    public Resident(Person owner, String residentPhoneNumber, String residentEmail) {
        super();
        setOwner(owner);
        setResidentPhoneNumber(residentPhoneNumber);
        setResidentEmail(residentEmail);
    }

    public Person getOwner() {
        return owner;
    }

    private void setOwner(Person owner) {
        if(owner == null){
            throw new IllegalArgumentException("owner should not be null");
        }
        this.owner = owner;
    }

    public String getResidentPhoneNumber() {
        return residentPhoneNumber;
    }

    public void setResidentPhoneNumber(String residentPhoneNumber) {
        if(residentPhoneNumber == null || residentPhoneNumber.isBlank()){
            throw new IllegalArgumentException("Resident Phone Number is required");
        }
        //phone validation
        if(!validPhoneRegex.matcher(residentPhoneNumber).matches()){
            throw new IllegalArgumentException("You should provide proper phone number");
        }
        this.residentPhoneNumber = residentPhoneNumber;
    }

    public String getResidentEmail() {
        return residentEmail;
    }

    public void setResidentEmail(String residentEmail) {
        if(residentEmail != null){
            if(residentEmail.isBlank()){
                throw new IllegalArgumentException("Resident Email should not be blank");
            }
            //email validation
            if(!validEmailRegex.matcher(residentEmail).matches()){
                throw new IllegalArgumentException("You should provide proper email");
            }
        }
        this.residentEmail = residentEmail;
    }

    @Override
    public void removeFromExtent() {
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "Resident{" +
                "owner=" + owner +
                ", residentPhoneNumber='" + residentPhoneNumber + '\'' +
                ", residentEmail='" + residentEmail + '\'' +
                '}';
    }
}
