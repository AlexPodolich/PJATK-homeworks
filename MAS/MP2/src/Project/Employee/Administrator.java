package Project.Employee;

import Project.Person;

import java.util.ArrayList;
import java.util.List;

public class Administrator extends Employee{
    private Employee empOwner;
    private List<String> adminResponsibilities = new ArrayList<>();

//    public Project.Employee.Administrator(Project.Employee.Employee empOwner, String workingPhoneNumber, String adminResponsibilities) {
//        super(empOwner.getOwner(), workingPhoneNumber);
//        setEmpOwner(empOwner);
//        addAdminResponsibility(adminResponsibilities);
//    }

    public Administrator(Employee oldRole, String adminResponsibility){
        super(oldRole);
        setEmpOwner(oldRole);
        addAdminResponsibility(adminResponsibility);

    }

    public Employee getEmpOwner() {
        return empOwner;
    }

    private void setEmpOwner(Employee empOwner) {
        if(empOwner == null){
            throw new IllegalArgumentException("empOwner is required");
        }
        this.empOwner = empOwner;
    }

    public List<String> getAdminResponsibilities() {
        return adminResponsibilities;
    }

    public void addAdminResponsibility(String adminResponsibility){
        if(adminResponsibility == null || adminResponsibility.isBlank()){
            throw new IllegalArgumentException("adminResponsibility is required");
        }
        adminResponsibilities.add(adminResponsibility);
    }

    public void removeAdminResponsibility(String adminResponsibility){
        if(adminResponsibility == null || adminResponsibility.isBlank()){
            throw new IllegalArgumentException("adminResponsibility is required");
        }
        adminResponsibilities.remove(adminResponsibility);
    }
    public void setAdminResponsibilities(List<String> adminResponsibilities) {
        this.adminResponsibilities = adminResponsibilities;
    }

    public void becomeSecurity(String patrolArea){
        //return new Project.Employee.Security(this, patrolArea);
    }

    public CleaningStuff becomeCleaningStuff(String cleaningArea){
        return new CleaningStuff(this, cleaningArea);
    }

    @Override
    public void removeFromExtent() {
        super.removeFromExtent();
    }


    @Override
    public String toString() {
        return "Administrator{" +
                "name= '" + getOwner().getName() + '\'' +
                ",surname= '" + getOwner().getSurname() + '\'' +
                ",working Number= '" + getEmpOwner().getWorkingPhoneNumbers() + '\'' +
                ",adminResponsibilities=" + adminResponsibilities +
                '}';
    }
}
