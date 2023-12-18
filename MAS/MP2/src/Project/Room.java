package Project;

import Project.Dormitory.Dormitory;
import Project.Employee.MainInspector;
import Project.Employee.RoomInspector;

import java.util.*;

public class Room extends ObjectPlus{
    private long id;
    private String roomNumber;
    private float roomPrice;
    private String roomDescription;
    private float roomSize;
    private static float minRoomSize = 25;
    private Dormitory dormitory;
    private Set<ReservedRoom> reservedRooms = new HashSet<>();
    private RoomInspector roomInspector;
    private MainInspector mainInspector;

    public Room(Dormitory dormitory, long id, String roomNumber, float roomPrice, String roomDescription, float roomSize) {
        super();
        setDormitory(dormitory);
        setId(id);
        setRoomNumber(roomNumber);
        setRoomPrice(roomPrice);
        setRoomDescription(roomDescription);
        setRoomSize(roomSize);

        addToExtent();
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        if(getExtent(Room.class).stream().anyMatch(m -> m.id == id)){
            throw new IllegalArgumentException("id must be unique");
        }
        if (id > 0){
            this.id = id;
        }else{
            throw new IllegalArgumentException("id must be a positive value");
        }
    }

    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        if(roomNumber == null || roomNumber.isBlank()){
            throw new IllegalArgumentException("room number is required");
        }
        this.roomNumber = roomNumber;
    }

    public float getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(float roomPrice) {
        if(roomPrice <= 0){
            throw new IllegalArgumentException("room price must be positive");
        }
        this.roomPrice = roomPrice;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        if(roomDescription == null || roomDescription.isBlank()){
            throw new IllegalArgumentException("room description is required");
        }
        this.roomDescription = roomDescription;
    }

    public float getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(float roomSize) {
        if(roomSize < minRoomSize){
            throw new IllegalArgumentException("Room Size must be > minRoom Size");
        }
        this.roomSize = roomSize;
    }

    public static float getMinRoomSize() {
        return minRoomSize;
    }

    public static void setMinRoomSize(float minRoomSize) {
        if(minRoomSize < 0){
            throw new IllegalArgumentException("min Room Size must be > 0");
        }
        Room.minRoomSize = minRoomSize;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    private void setDormitory(Dormitory dormitory) {
        //check if room is not the same as the old one
        if(this.dormitory != dormitory){
            if (this.dormitory == null && dormitory != null){
                //create new association
                this.dormitory = dormitory;
                dormitory.addRoom(this);
            }else if(this.dormitory != null && dormitory == null) {
                //remove association
                Dormitory tmp = this.dormitory;
                this.dormitory = null;
                tmp.removeRoom(this);
            }else if(this.dormitory != null && dormitory != null){
                //change to another association
                Dormitory tmp = this.dormitory;
                tmp.removeRoom(this);
                this.dormitory = dormitory;
                dormitory.addRoom(this);
            }
        }
    }

    public Set<ReservedRoom> getReservedRooms() {
        return Collections.unmodifiableSet(reservedRooms);
    }

    public void addReservedRoom(ReservedRoom reservedRoom){
        if(reservedRoom == null){
            throw new IllegalArgumentException("reservedRoom is required");
        }
        if(reservedRoom.getRoom() != this){
            //not consistent
            throw new IllegalArgumentException("not allow to change reservedRoom");
        }
        reservedRooms.add(reservedRoom);
    }

    public void removeReservedRoom(ReservedRoom reservedRoom){
        if(reservedRoom == null){
            throw new IllegalArgumentException("reservedRoom is exist");
        }
        if(!this.reservedRooms.contains(reservedRoom)){
            return;
        }
        reservedRooms.remove(reservedRoom);
        reservedRoom.remove();
    }

    public RoomInspector getRoomInspector() {
        return roomInspector;
    }

    public void setRoomInspector(RoomInspector roomInspector) {
        //check if roomInspector is not the same as the old one
        if(this.roomInspector != roomInspector) {
            if (this.roomInspector == null && roomInspector != null) {
                //create new association
                this.roomInspector = roomInspector;
                roomInspector.addRoom(this);
            } else if (this.roomInspector != null && roomInspector == null) {
                //remove association
                RoomInspector tmp = this.roomInspector;
                this.roomInspector = null;
                tmp.removeRoom(this);
            } else if (this.roomInspector != null && roomInspector != null) {
                //change to another association
                RoomInspector tmp = this.roomInspector;
                tmp.removeRoom(this);
                this.roomInspector = roomInspector;
                roomInspector.addRoom(this);
            }
        }
    }

    public void setRoomInspector(MainInspector mainInspector) {
        //check if roomInspector is not the same as the old one
        if(this.mainInspector != mainInspector) {
            if (this.mainInspector == null && mainInspector != null) {
                //create new association
                this.mainInspector = mainInspector;
                roomInspector.addRoom(this);
            } else if (this.mainInspector != null && mainInspector == null) {
                //remove association
                MainInspector tmp = this.mainInspector;
                this.mainInspector = null;
                tmp.removeRoom(this);
            } else if (this.roomInspector != null && roomInspector != null) {
                //change to another association
                MainInspector tmp = this.mainInspector;
                tmp.removeRoom(this);
                this.mainInspector = mainInspector;
                mainInspector.addRoom(this);
            }
        }
    }

    @Override
    public void removeFromExtent() {
        setDormitory(null);
        super.removeFromExtent();

        // check for room ins
        if(roomInspector != null) {
            roomInspector.removeRoom(this);
        }

       //remove reservations
        if(reservedRooms != null && reservedRooms.size() > 0){
            for(ReservedRoom reservedRoom : reservedRooms){
                if(reservedRoom == null){
                    throw new IllegalArgumentException("Error");
                }
                reservedRoom.remove();
            }
        }
    }

    @Override
    public String toString() {
        if(this.dormitory == null)
            return null;
        return "Room{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomPrice=" + roomPrice +
                ", roomDescription='" + roomDescription + '\'' +
                ", roomSize=" + roomSize +
                ", dormitory=" + dormitory.getName() +
                ", reservedRooms=" + reservedRooms+
                '}';
    }
}
