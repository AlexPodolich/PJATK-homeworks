package Interfaces;

import java.util.List;
import java.util.Map;

import Project.Room;

public interface IRoomInspector extends IEmployee{

    public List<String> getInspectedRooms();

    public void addInspectedRoom(String inspectedRoom);
    public Map<Long, Room> getRoomsWithProblems();

    public List<Room> getRoomList();

    public Room getRoom(long room_id);

    public void addRoom(Room room);

    public void removeRoom(Room room);
}
