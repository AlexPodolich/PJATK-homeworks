package Multi;

import java.util.ArrayList;
import java.util.List;

public class Administrator extends Employee {
    private List<String> adminResponsibilities = new ArrayList<>();

    public Administrator(long id, String name, String surname, String workingPhoneNumber, String adminResponsibility) {
        super(id, name, surname, workingPhoneNumber);
        addAdminResponsibility(adminResponsibility);
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
        if(adminResponsibilities == null){
            throw new IllegalArgumentException("adminResponsibilities is required");
        }
        for (String responsability : adminResponsibilities) {
            if(responsability == null || responsability.isBlank()){
                throw new IllegalArgumentException("responsability is required");
            }
        }
        this.adminResponsibilities = adminResponsibilities;
    }


    @Override
    public void removeFromExtent() {
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", workingPhoneNumbers=" + getWorkingPhoneNumbers() +
                ",adminResponsibilities=" + adminResponsibilities +
                '}';
    }
}
