package Dynamic;


public class Security extends Employee {

    private String patrolArea;

    public Security(long id, String name, String surname, String workingPhoneNumber){
        super(id, name, surname, workingPhoneNumber);
    }

    public Security(Employee oldRole, String patrolArea) {
        super(oldRole);
        setPatrolArea(patrolArea);
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

    @Override
    public void removeFromExtent() {
        super.removeFromExtent();
    }


    @Override
    public String toString() {
        return "Security{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", workingPhoneNumbers=" + getWorkingPhoneNumbers() +
                ", patrolArea=" + patrolArea +
                '}';
    }
}
