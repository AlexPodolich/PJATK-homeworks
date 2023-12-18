import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Resident extends ObjectPlus{
    private long id;
    private String residentName;
    private String residentSurname;
    private String residentPhoneNumber;
    private String residentEmail;
    private static Pattern validEmailRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static Pattern validPhoneRegex = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$", Pattern.CASE_INSENSITIVE);

    //private Set<Room> rooms = new HashSet<>();

    public Resident(long id, String residentName, String residentSurname, String residentPhoneNumber, String residentEmail) {
        super();
        setId(id);
        setResidentName(residentName);
        setResidentSurname(residentSurname);
        setResidentPhoneNumber(residentPhoneNumber);
        setResidentEmail(residentEmail);

        addToExtent();
    }

    public Resident(long id, String residentName, String residentSurname, String residentPhoneNumber) {
        super();
        setId(id);
        setResidentName(residentName);
        setResidentSurname(residentSurname);
        setResidentPhoneNumber(residentPhoneNumber);

        addToExtent();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(getExtent(Resident.class).stream()
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

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        if(residentName == null || residentName.isBlank()){
            throw new IllegalArgumentException("Resident Name is required");
        }
        this.residentName = residentName;
    }

    public String getResidentSurname() {
        return residentSurname;
    }

    public void setResidentSurname(String residentSurname) {
        if(residentSurname == null || residentSurname.isBlank()){
            throw new IllegalArgumentException("Resident Surname is required");
        }
        this.residentSurname = residentSurname;
    }

    public String getResidentPhoneNumber() {
        return residentPhoneNumber;
    }

    public void setResidentPhoneNumber(String residentPhoneNumber) {
        if(residentPhoneNumber == null || residentPhoneNumber.isBlank()){
            throw new IllegalArgumentException("Resident Phone Number is required");
        }
        //phone validation
        if(!validPhoneRegex.matcher(residentPhoneNumber).matches()){
            throw new IllegalArgumentException("You should provide proper phone number");
        }
        this.residentPhoneNumber = residentPhoneNumber;
    }

    public String getResidentEmail() {
        return residentEmail;
    }

    public void setResidentEmail(String residentEmail) {
        if(residentEmail != null){
            if(residentEmail.isBlank()){
                throw new IllegalArgumentException("Resident Email should not be blank");
            }
            //email validation
            if(!validEmailRegex.matcher(residentEmail).matches()){
                throw new IllegalArgumentException("You should provide proper email");
            }
        }
        this.residentEmail = residentEmail;
    }

//    public Set<Room> getRooms() {
//        return Collections.unmodifiableSet(rooms);
//    }
//
//    public void addRoom(Room room) {
//        if(room == null){
//            throw new IllegalArgumentException("Room is required");
//        }
//        if(!this.rooms.contains(room)){
//            this.rooms.add(room);
//            room.addResident(this);
//        }
//    }
//
//    public void removeRoom(Room room) {
//        if(!rooms.contains(room)){
//            throw new IllegalArgumentException("Room doesn't exists");
//        }
//        rooms.remove(room);
//        room.removeResident(this);
//    }


    @Override
    public String toString() {
        return "Resident with " +
                "id=" + id +
                ", residentName='" + residentName + '\'' +
                ", residentSurname='" + residentSurname + '\'' +
                ", residentPhoneNumber='" + residentPhoneNumber + '\'' +
                ", residentEmail='" + residentEmail + '\'' +
                '}';
    }


}
