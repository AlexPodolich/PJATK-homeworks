package MultiAspect;


import java.util.regex.Pattern;

public class Resident extends Person {
    private String residentPhoneNumber;
    private String residentEmail;
    private static Pattern validEmailRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static Pattern validPhoneRegex = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$", Pattern.CASE_INSENSITIVE);

    public Resident(PersonHealth health, long id, String name, String surname, String medicalCard, String demand, String residentPhoneNumber, String residentEmail) {
        super(health, id, name, surname, medicalCard, demand);
        setResidentPhoneNumber(residentPhoneNumber);
        setResidentEmail(residentEmail);
    }

    public String getResidentPhoneNumber() {
        return residentPhoneNumber;
    }

    public void setResidentPhoneNumber(String residentPhoneNumber) {
        if(residentPhoneNumber == null || residentPhoneNumber.isBlank()){
            throw new IllegalArgumentException("Project.Project.Resident.Resident Phone Number is required");
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
                throw new IllegalArgumentException("Project.Project.Resident.Resident Email should not be blank");
            }
            //email validation
            if(!validEmailRegex.matcher(residentEmail).matches()){
                throw new IllegalArgumentException("You should provide proper email");
            }
        }
        this.residentEmail = residentEmail;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "health=" + getHealth() +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", residentPhoneNumber='" + residentPhoneNumber + '\'' +
                ", residentEmail='" + residentEmail + '\'' +
                '}';
    }
}
