package Project.Employee;

import Enum.EmployeeType;
import Project.Employment;
import Project.ObjectPlus;
import Project.Person;
import Project.Room;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Employee extends ObjectPlus {
    private Person owner;
    private List<String> workingPhoneNumbers = new ArrayList<>();
    private Set<Employment> employments = new HashSet<>();
    private static Pattern validPhoneRegex = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$", Pattern.CASE_INSENSITIVE);

    private Administrator administratorRole;
    private Security securityRole;
    private CleaningStuff cleaningStuffRole;
    private RoomInspector roomInspectorRole;

    public Employee(Person owner) {
        setOwner(owner);

        //addToExtent();
    }


    public Employee(Person owner, String workingPhoneNumber, EmployeeType employeeType,
                    String adminResponsibility, // for Project.Project.Employee.Administrator
                    String patrolArea,    // for Project.Project.Employee.Security
                    String cleaningArea,  // for Cleaning stuff
                    String inspectedRoom // for Project.Project.Employee.RoomInspector
    ){
        setOwner(owner);
        addWorkingPhoneNumber(workingPhoneNumber);

        if(employeeType == EmployeeType.ADMINISTRATOR){
            administratorRole = new Administrator(this, adminResponsibility);
        }else{
            this.administratorRole = null;
        }

        if(employeeType == EmployeeType.SECURITY){
            this.securityRole = new Security(this, patrolArea);
        }else{
            this.securityRole = null;
        }

        if(employeeType == EmployeeType.CLEANING_STUFF){
            this.cleaningStuffRole = new CleaningStuff(this, cleaningArea);
        }else{
            this.cleaningStuffRole = null;
        }

        if(employeeType == EmployeeType.ROOM_INSPECTOR){
            this.roomInspectorRole = new RoomInspector(this, inspectedRoom);
        }else{
            this.roomInspectorRole = null;
        }
    }

    public Employee(Employee oldRole) {
        if(oldRole == null){
            throw new IllegalArgumentException("oldRole is required");
        }
        this.owner = oldRole.owner;
        this.workingPhoneNumbers = oldRole.workingPhoneNumbers;

        if(oldRole.roomInspectorRole != null){
            oldRole.getRoomInspectorRole().removeFromExtent();
            oldRole.roomInspectorRole = null;
        }

        if(oldRole.administratorRole != null){
            oldRole.getAdministratorRole().removeFromExtent();
            oldRole.administratorRole = null;
        }

        if(oldRole.securityRole != null){
            oldRole.getSecurityRole().removeFromExtent();
            oldRole.securityRole = null;
        }

        if(oldRole.cleaningStuffRole != null){
            oldRole.getCleaningStuffRole().removeFromExtent();
            oldRole.cleaningStuffRole = null;
        }
    }

    public void setAdministratorRole(Administrator administratorRole) {
        this.administratorRole = administratorRole;
    }

    public void setSecurityRole(Security securityRole) {
        this.securityRole = securityRole;
    }

    public void setCleaningStuffRole(CleaningStuff cleaningStuffRole) {
        this.cleaningStuffRole = cleaningStuffRole;
    }

    public void setRoomInspectorRole(RoomInspector roomInspectorRole) {
        this.roomInspectorRole = roomInspectorRole;
    }

    public Person getOwner() {
        return owner;
    }

    private void setOwner(Person owner) {
        if(owner == null){
            throw new IllegalArgumentException("owner is required");
        }
        this.owner = owner;
    }

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



    public Set<Employment> getEmployments(){
        return Collections.unmodifiableSet(employments);
    }

    public void addEmployment(Employment e){
        if(e == null){
            throw new IllegalArgumentException("Employment is required");
        }
        if(e.getEmployee() != this){
            //not consistent
            throw new IllegalArgumentException("not allow to change employment");
        }
        employments.add(e);
    }

    public void removeEmployment(Employment e){
        if(e == null){
            throw new IllegalArgumentException("Employment doesn't exist");
        }
        if(!this.employments.contains(e)) {
            return;
        }
        employments.remove(e);
        e.remove();
    }

    //class Method
    public static List<Employee> findByName(String searchedName){
        if(searchedName == null){
            throw new IllegalArgumentException("searched name is required");
        }
        List<Employee> extent = getExtent(Employee.class);

        return extent.stream()
                .filter(m -> m.owner.getName().equals(searchedName))
                .collect(Collectors.toList());
    }

    public Administrator getAdministratorRole() {
        if(administratorRole == null){
            throw new IllegalArgumentException("you are not allowed to do it");
        }
        return administratorRole;
    }

    public Security getSecurityRole() {
        if(securityRole == null){
            throw new IllegalArgumentException("you are not allowed to do it");
        }
        return securityRole;
    }

    public CleaningStuff getCleaningStuffRole() {
        if(securityRole == null){
            throw new IllegalArgumentException("you are not allowed to do it");
        }
        return cleaningStuffRole;
    }

    public RoomInspector getRoomInspectorRole() {
        if(roomInspectorRole == null){
            throw new IllegalArgumentException("you are not allowed to do it");
        }
        return roomInspectorRole;
    }



    @Override
    public void removeFromExtent() {
        super.removeFromExtent();
        if(employments != null && employments.size() > 0){
            for(Employment employment : employments){
                employment.remove();
            }
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "owner=" + owner +
                ", workingPhoneNumbers=" + workingPhoneNumbers +
                ", employments=" + employments +
                ", administratorRole=" + administratorRole +
                ", securityRole=" + securityRole +
                ", cleaningStuffRole=" + cleaningStuffRole +
                ", roomInspectorRole=" + roomInspectorRole +
                '}';
    }
}
