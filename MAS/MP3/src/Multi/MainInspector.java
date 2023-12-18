package Multi;

import java.util.*;

public class MainInspector extends Administrator implements IRoomInspector {
    private List<String> inspectedAreas = new ArrayList<>();
    private List<String> inspectedRooms = new ArrayList<>();

    public MainInspector(long id, String name, String surname, String workingPhoneNumber, String adminResponsibility, String inspectedArea, String inspectedRoom) {
        super(id, name, surname, workingPhoneNumber, adminResponsibility);
        addInspectedArea(inspectedArea);
        addInspectedRoom(inspectedRoom);
    }

    public void addInspectedArea(String inspectedArea){
        if(inspectedArea == null || inspectedArea.isBlank()){
            throw new IllegalArgumentException("inspectedArea is required");
        }
        inspectedAreas.add(inspectedArea);
    }

    public void removeInspectedArea(String inspectedArea){
        if(inspectedArea == null || inspectedArea.isBlank()){
            throw new IllegalArgumentException("inspectedArea is required");
        }
        inspectedAreas.remove(inspectedArea);
    }

    public void addInspectedRoom(String inspectedRoom) {
        if(inspectedRoom == null || inspectedRoom.isBlank()){
            throw new IllegalArgumentException("inspectedRoom is required");
        }
        inspectedRooms.add(inspectedRoom);
    }

    public void removeInspectedRoom(String inspectedRoom) {
        if(inspectedRoom == null || inspectedRoom.isBlank()){
            throw new IllegalArgumentException("inspectedRoom is required");
        }
        inspectedRooms.remove(inspectedRoom);
    }


    public List<String> getInspectedRooms() {
        return Collections.unmodifiableList(inspectedRooms);
    }

    public List<String> getInspectedAreas() {
        return Collections.unmodifiableList(inspectedAreas);
    }

    @Override
    public void removeFromExtent() {
        super.removeFromExtent();
    }


    @Override
    public String toString() {
        return "MainInspector{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", workingPhoneNumbers=" + getWorkingPhoneNumbers() +
                ", adminResponsibilities=" + getAdminResponsibilities() +
                ", inspectedAreas=" + inspectedAreas +
                ", inspectedRooms=" + inspectedRooms +
                '}';
    }
}
