package Project.Employee;

public class CleaningStuff extends Employee{
    private Employee empOwner;
    private String cleaningArea;

/*    public Project.Employee.CleaningStuff(Project.Employee.Employee empOwner,String workingPhoneNumber,  String cleaningArea) {
        super(empOwner.getOwner(), workingPhoneNumber);
        setEmpOwner(empOwner);
        setCleaningArea(cleaningArea);
    }*/

    public CleaningStuff(Employee oldRole, String cleaningArea) {
        super(oldRole);
        setEmpOwner(oldRole);
        this.cleaningArea = cleaningArea;

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

    public String getCleaningArea() {
        return cleaningArea;
    }

    public void setCleaningArea(String cleaningArea) {
        if(cleaningArea == null || cleaningArea.isBlank()){
            throw new IllegalArgumentException("cleaningArea is required");
        }
        this.cleaningArea = cleaningArea;
    }

    public Administrator becomeAdministrator(String adminResponsibility){
        return new Administrator(this, adminResponsibility);
    }

    public Security becomeSecurity(String patrolArea){
        return new Security(this, patrolArea);
    }

    @Override
    public void removeFromExtent() {
        super.removeFromExtent();

    }

    @Override
    public String toString() {
        return "CleaningStuff{" +
                "empOwner=" + empOwner +
                "name= '" + getOwner().getName() + '\'' +
                ",surname= '" + getOwner().getSurname() + '\'' +
                ",working Number= '" + getEmpOwner().getWorkingPhoneNumbers() + '\'' +
                ", cleaningArea='" + cleaningArea + '\'' +
                '}';
    }
}
