import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Room extends ObjectPlus{
    private long id;
    private String roomNumber;
    private float roomPrice;
    private String roomDescription;
    private float roomSize;
    private static float minRoomSize = 25;
    //private Dormitory dormitory;
    //private Set<Resident> residents = new HashSet<>();

    public Room(long id, String roomNumber, float roomPrice, String roomDescription, float roomSize) {
        super();
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

    public void setId(long id) {
        if(getExtent(Room.class).stream()
                .filter(m -> m.id == id)
                .collect(Collectors.toList()).size() > 0){
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
            throw new IllegalArgumentException("Room Size must be > min Room Size");
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

//    public Dormitory getDormitory() {
//        return dormitory;
//    }
//
//    public void setDormitory(Dormitory dormitory) {
//        //check if room is not the same as the old one
//        if(this.dormitory != dormitory){
//            if (this.dormitory == null && dormitory != null){
//                //create new association
//                this.dormitory = dormitory;
//                dormitory.addRoom(this);
//            }else if(this.dormitory != null && dormitory == null) {
//                //remove association
//                Dormitory tmp = this.dormitory;
//                this.dormitory = null;
//                tmp.removeRoom(this);
//            }else if(this.dormitory != null && dormitory != null){
//                //change to another association
//                Dormitory tmp = this.dormitory;
//                tmp.removeRoom(this);
//                this.dormitory = dormitory;
//                dormitory.addRoom(this);
//            }
//        }
//    }
//
//    public Set<Resident> getResidents() {
//        return Collections.unmodifiableSet(residents);
//    }
//
//    public void addResident(Resident resident) {
//        if(resident == null){
//            throw new IllegalArgumentException("resident is required");
//        }
//        if(!this.residents.contains(resident)){
//            this.residents.add(resident);
//            resident.addRoom(this);
//        }
//    }
//
//    public void removeResident(Resident resident) {
//        if(residents.contains(resident)){
//            throw new IllegalArgumentException("resident doesn't exists");
//        }
//        residents.remove(resident);
//        resident.removeRoom(this);
//    }


    @Override
    public String toString() {
        return "Room with " +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", roomPrice=" + roomPrice +
                ", roomDescription='" + roomDescription + '\'' +
                ", roomSize=" + roomSize +
                '}';
    }
}
