package Dynamic;


public class CleaningStuff extends Employee {
    private String cleaningArea;

    public CleaningStuff(long id, String name, String surname, String workingPhoneNumber){
        super(id, name, surname, workingPhoneNumber);
    }
    public CleaningStuff(Employee oldRole, String cleaningArea) {
        super(oldRole);
        this.cleaningArea = cleaningArea;
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

    @Override
    public void removeFromExtent() {
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "CleaningStuff{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", workingPhoneNumbers=" + getWorkingPhoneNumbers() +
                ", cleaningArea=" + cleaningArea +
                '}';
    }

}
