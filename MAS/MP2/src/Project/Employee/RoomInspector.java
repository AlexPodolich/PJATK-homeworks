package Project.Employee;

import Interfaces.IRoomInspector;
import Project.Person;
import Project.Room;

import java.util.*;

public class RoomInspector extends Employee implements IRoomInspector {

    private Employee empOwner;
    private List<String> inspectedRooms = new ArrayList<>();
    private Map<Long, Room> roomsWithProblems = new HashMap<>();

    public RoomInspector(Employee oldRole, String inspectedRoom) {
        super(oldRole);
        setEmpOwner(oldRole);
        addInspectedRoom(inspectedRoom);
        roomsWithProblems = new HashMap<>();

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

    public List<String> getInspectedRooms() {
        return inspectedRooms;
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

    public Map<Long, Room> getRoomsWithProblems() {
        return Collections.unmodifiableMap(roomsWithProblems);
    }

    public List<Room> getRoomList(){
        return new ArrayList<>(roomsWithProblems.values());
    }

    public Room getRoom(long room_id){
        return roomsWithProblems.get(room_id);
    }

    public void addRoom(Room room){
        if(room == null){
            throw new IllegalArgumentException("room is required");
        }
        roomsWithProblems.put(room.getId(), room);
        room.setRoomInspector(this);
    }

    public void removeRoom(Room room){
        if(room == null){
            throw new IllegalArgumentException("room is required");
        }
        if(!roomsWithProblems.containsValue(room)){
            return;
        }
        roomsWithProblems.remove(room.getId());
        room.setRoomInspector((RoomInspector) null);
        room.setRoomInspector((MainInspector) null);
    }

    @Override
    public void removeFromExtent() {
        super.removeFromExtent();
        //remove room inspector's associations

        if(roomsWithProblems != null && roomsWithProblems.size() > 0){
            for (Room room : roomsWithProblems.values()) {
                room.setRoomInspector((RoomInspector) null);
                room.setRoomInspector((MainInspector) null);
            }
        }
    }


    @Override
    public String toString() {
        return "RoomInspector{" +
                "name= '" + getOwner().getName() + '\'' +
                ",surname= '" + getOwner().getSurname() + '\'' +
                ",working Number= '" + getEmpOwner().getWorkingPhoneNumbers() + '\'' +
                "roomsWithProblems=" + roomsWithProblems +
                '}';
    }
}
