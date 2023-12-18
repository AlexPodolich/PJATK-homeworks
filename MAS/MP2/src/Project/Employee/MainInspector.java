package Project.Employee;

import Interfaces.IRoomInspector;
import Project.Room;

import java.util.*;

public class MainInspector extends Administrator implements IRoomInspector {
    private List<String> inspectedAreas = new ArrayList<>();
    private List<String> inspectedRooms = new ArrayList<>();
    private Map<Long, Room> roomsWithProblems = new HashMap<>();

    public MainInspector(Employee owner, String adminResponsibilities, String inspectedArea, String inspectedRoom) {
        super(owner, adminResponsibilities);
        addInspectedRoom(inspectedRoom);

        addInspectedArea(inspectedArea);

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

    public List<String> getInspectedRooms() {
        return inspectedRooms;
    }

    public void addInspectedRoom(String inspectedRoom) {
        inspectedRooms.add(inspectedRoom);
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
}
