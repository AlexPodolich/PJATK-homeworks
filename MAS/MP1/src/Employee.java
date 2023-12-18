import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Employee extends ObjectPlus {
    private long id;
    private String name;
    private String surname;
    private float monthlySalary;
    private List<String> workingPhoneNumbers = new ArrayList<>();
    private List<String> penalties = new ArrayList<>();
    private static Pattern validPhoneRegex = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$", Pattern.CASE_INSENSITIVE);

//    private Dormitory employer;

    public Employee(long id, String name, String surname, float monthlySalary, String workingPhoneNumber) {
        super();
        setId(id);
        setName(name);
        setSurname(surname);
        setMonthlySalary(monthlySalary);
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

    public float getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(float monthlySalary) {
        if(monthlySalary <= 0){
            throw new IllegalArgumentException("monthly Salary must be positive");
        }
        this.monthlySalary = monthlySalary;
    }

    public List<String> getPenalties() {
        return Collections.unmodifiableList(penalties);
    }

    public void addPenalty(String penalty){
        if(penalty == null || penalty.isBlank()){
            throw new IllegalArgumentException("penalty is required");
        }
        this.penalties.add(penalty);
    }

    public void removePenalty(String penalty){
        if(penalty == null || penalty.isBlank()){
            throw new IllegalArgumentException("penalty is required");
        }
        if(!penalties.contains(penalty)){
            throw new IllegalArgumentException("penalty doesn't exist");
        }
        this.penalties.remove(penalty);
    }

    public float getActualSalary(){
        int numOfPenalties = penalties.size();
        float fineForPenalties = 100.0f * numOfPenalties;
        float actualSalary = monthlySalary - fineForPenalties;
        return actualSalary;
    }

//    public Dormitory getEmployer() {
//        return employer;
//    }
//
//    public void setEmployer(Dormitory employer) {
//        //check if employer is not the same as the old one
//        if(this.employer != employer){
//            if (this.employer == null && employer != null){
//                //create new association
//                this.employer = employer;
//                employer.addEmp(this);
//            }else if(this.employer != null && employer == null) {
//                //remove association
//                Dormitory tmp = this.employer;
//                this.employer = null;
//                tmp.removeEmp(this);
//            }else if(this.employer != null && employer != null){
//                //change to another association
//                Dormitory tmp = this.employer;
//                tmp.removeEmp(this);
//                this.employer = employer;
//                employer.addEmp(this);
//            }
//        }
//    }

    public List<String> getWorkingPhoneNumbers() {
        return Collections.unmodifiableList(workingPhoneNumbers);
    }

    public void addWorkingPhoneNumber(String phoneNum){
        if(phoneNum == null || phoneNum.isBlank()){
            throw new IllegalArgumentException("phoneNum is required");
        }
        //phone validation
        if(!validPhoneRegex.matcher(phoneNum).matches()){
            throw new IllegalArgumentException("You should provide proper phone number");
        }
        workingPhoneNumbers.add(phoneNum);
    }

    public void removeWorkingPhoneNumber(String phoneNum){
        if(phoneNum == null || phoneNum.isBlank()){
            throw new IllegalArgumentException("phoneNum is required");
        }
        if(!workingPhoneNumbers.contains(phoneNum)){
            throw new IllegalArgumentException("phoneNum doesn't exist");
        }
        if(workingPhoneNumbers.size() == 1){
            throw new IllegalArgumentException("you can not remove last working number");
        }
        workingPhoneNumbers.remove(phoneNum);
    }

    //class Method
    public static List<Employee> findByName(String searchedName){
        if(searchedName == null || searchedName.isBlank()){
            throw new IllegalArgumentException("searched name is required");
        }
        List<Employee> extent = getExtent(Employee.class);

        return extent.stream()
                .filter(m -> m.name.equals(searchedName))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Employee with " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", monthlySalary=" + monthlySalary +
                ", workingPhoneNumbers=" + workingPhoneNumbers +
                ", penalties=" + penalties
                ;
    }
}
