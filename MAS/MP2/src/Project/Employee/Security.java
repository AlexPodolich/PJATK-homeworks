package Project.Employee;

import Project.Room;

public class Security extends Employee{

    private Employee empOwner;
    private String patrolArea;

/*    public Project.Employee.Security(Project.Employee.Employee empOwner,String workingPhoneNumber, String patrolArea) {
        super(empOwner.getOwner(), workingPhoneNumber);
        setEmpOwner(empOwner);
        setPatrolArea(patrolArea);
    }*/

    public Security(Employee oldRole, String patrolArea) {
        super(oldRole);
        setEmpOwner(oldRole);
        setPatrolArea(patrolArea);
//        if(oldRole.getClass().equals(RoomInspector.class)){
//            oldRole.getRoomInspectorRole().removeFromExtent();
//        }
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

    public String getPatrolArea() {
        return patrolArea;
    }

    public void setPatrolArea(String patrolArea) {
        if(patrolArea == null || patrolArea.isBlank()){
            throw new IllegalArgumentException("patrolArea is required");
        }
        this.patrolArea = patrolArea;
    }

    public Administrator becomeAdministrator(String adminResponsibility){
        return new Administrator(this, adminResponsibility);
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
        return "Security{" +
                "name= '" + getOwner().getName() + '\'' +
                ",surname= '" + getOwner().getSurname() + '\'' +
                ",working Number= '" + getEmpOwner().getWorkingPhoneNumbers() + '\'' +
                ",patrolArea='" + patrolArea + '\'' +
                '}';
    }
}
