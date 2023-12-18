import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Dormitory extends ObjectPlus{
    private long id;
    private String name;
    private Address address;
    private DormitoryRating rating;
//    private Set<Room> rooms = new HashSet<>();
//    private Set<Employee> employees = new HashSet<>();

    public Dormitory(long id, String name, Address address, DormitoryRating rating) {
        super();
        setId(id);
        setName(name);
        setAddress(address);
        setRating(rating);

        addToExtent();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(getExtent(Dormitory.class).stream()
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("name is required");
        }
        this.name = name;
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

    public DormitoryRating getRating() {
        return rating;
    }

    public void setRating(DormitoryRating rating) {
        if(rating == null){
            throw new IllegalArgumentException("rating is required");
        }
        this.rating = rating;
    }

//    public Set<Room> getRooms() {
//        return Collections.unmodifiableSet(rooms);
//    }
//
//    public Set<Employee> getEmployees() {
//        return Collections.unmodifiableSet(employees);
//    }
//
//    public void addEmp(Employee e){
//        if(e == null){
//            throw new IllegalArgumentException("Employee is required");
//        }
//        if (!this.employees.contains(e)) {
//            e.setEmployer(this);
//            this.employees.add(e);
//        }
//    }
//
//    public void removeEmp(Employee e){
//        employees.remove(e);
//        e.setEmployer(null);
//    }
//
//    public void addRoom(Room room){
//        if(room == null){
//            throw new IllegalArgumentException("Room is required");
//        }
//        if(!rooms.contains(room)){
//            room.setDormitory(this);
//            this.rooms.add(room);
//        }
//    }
//
//    public void removeRoom(Room room){
//        rooms.remove(room);
//        room.setDormitory(null);
//    }


    @Override
    public String toString() {
        return "Dormitory with" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", rating=" + rating +
                '}';
    }
}
