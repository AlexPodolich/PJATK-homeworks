package Multi;


import java.util.*;

public class RoomInspector extends Employee implements IRoomInspector {

    private List<String> inspectedRooms = new ArrayList<>();

    public RoomInspector(long id, String name, String surname, String workingPhoneNumber, String inspectedRoom) {
        super(id, name, surname, workingPhoneNumber);
        addInspectedRoom(inspectedRoom);
    }

    public List<String> getInspectedRooms() {
        return Collections.unmodifiableList(inspectedRooms);
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

    @Override
    public void removeFromExtent() {
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "RoomInspector{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", workingPhoneNumbers=" + getWorkingPhoneNumbers() +
                ", inspectedRooms=" + inspectedRooms +
                '}';
    }
}
