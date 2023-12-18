package Project.Dormitory;

import java.util.*;
import java.util.stream.Collectors;

import Enum.DormitoryRating;
import Project.Employment;
import Project.ObjectPlus;
import Project.Room;

public class Dormitory extends ObjectPlus {
    private long id;
    private String name;
    private Address address;
    private DormitoryRating rating;
    private Set<Room> rooms = new HashSet<>();
    private Set<Employment> employments = new HashSet<>();

    public Dormitory(long id, String name, Address address, DormitoryRating rating) {
        super();
        setId(id);
        setName(name);
        setAddress(address);
        setRating(rating);

        addToExtent();
    }

    public Dormitory(long id, String name, Address address, Room room, DormitoryRating rating) {
        super();
        setId(id);
        setName(name);
        setAddress(address);
        addRoom(room);
        setRating(rating);

        addToExtent();
    }

    public void createRoom(long id, String roomNumber, float roomPrice, String roomDescription, float roomSize){
        Room newRoom = new Room(this, id, roomNumber, roomPrice, roomDescription, roomSize);
        rooms.add(newRoom);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id > 0){
            this.id = id;
        }else{
            throw new IllegalArgumentException("id must be a positive value");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("name is required");
        }
        this.name = name;
    }

    public DormitoryRating getRating() {
        return rating;
    }

    public void setRating(DormitoryRating rating) {
        if(rating == null){
            throw new IllegalArgumentException("rating is required");
        }
        this.rating = rating;
    }

    //ordered constraint
    public List<Room> getRooms() {
        List<Room> orderedRooms = rooms.stream().sorted(Comparator.comparing(Room::getRoomNumber, String.CASE_INSENSITIVE_ORDER)).collect(Collectors.toList());
        return Collections.unmodifiableList(orderedRooms);
    }

    public void addRoom(Room room){
        if(room == null){
            throw new IllegalArgumentException("Room is required");
        }
        if(room.getDormitory() != this){
            throw new IllegalArgumentException("Cant add room from another dorm");
        }
        if(!rooms.contains(room)){
            this.rooms.add(room);
        }
    }

    public void removeRoom(Room room){
        if(room == null){
            throw new IllegalArgumentException("Project.Project.Room doesn't exist");
        }
        if(!rooms.contains(room)){
            return;
        }
        rooms.remove(room);
        room.removeFromExtent();
    }

    public Set<Employment> getEmployments(){
        return Collections.unmodifiableSet(employments);
    }

    public void setAddress(Address address) {
        if(name == null){
            throw new IllegalArgumentException("address is required");
        }
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void addEmployment(Employment e){
        if(e == null){
            throw new IllegalArgumentException("Project.Project.Employment is required");
        }
        if(e.getDormitory() != this){
            //not consistent
            throw new IllegalArgumentException("not allow to change employment");
        }
        employments.add(e);
    }

    public void removeEmployment(Employment e){
        if(e == null){
            throw new IllegalArgumentException("Project.Project.Employment doesn't exist");
        }
        if(!this.employments.contains(e)){
            return;
        }
        employments.remove(e);
        e.remove();
    }

    @Override
    public void removeFromExtent() {
        super.removeFromExtent();
        if(rooms != null && rooms.size() > 0){
            for(Room room : rooms){
                room.removeFromExtent();
            }
        }

        if(employments != null && employments.size() > 0){
            for(Employment employment : employments){
                employment.remove();
            }
        }

        //remove assoc with room ins and reservation
    }


    @Override
    public String toString() {
        return "Project.Project.Dormitory.Dormitory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", rating=" + rating +
                ", rooms=" + rooms +
                ", employments=" + employments +
                '}';
    }
}
