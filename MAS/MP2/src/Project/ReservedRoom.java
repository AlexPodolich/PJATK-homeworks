package Project;

import Project.Resident.Resident;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class ReservedRoom extends ObjectPlus{
    private Room room;
    private Resident resident;
    private LocalDate startDate;
    private LocalDate endDate;
    private float rent;

    public ReservedRoom(Room room, Resident resident, LocalDate startDate, LocalDate endDate, float rent) {
        super();

        //comenting this section we are doing bag/history constraint
        //prevent from having the same pair twice
/*        for (ReservedRoom reservedRoom : room.getReservedRooms()){
            if(reservedRoom.getResident().equals(resident)){
                throw new IllegalArgumentException("ReservedRoom is already exists");
            }
        }*/
        setRoom(room);
        setResident(resident);
        setStartDate(startDate);
        setEndDate(endDate);
        setRent(rent);

        addToExtent();
    }

    public Room getRoom() {
        return room;
    }

    private void setRoom(Room room) {
        if(room == null){
            throw new IllegalArgumentException("room is required");
        }
        this.room = room;
        room.addReservedRoom(this);
    }

    public Resident getResident() {
        return resident;
    }

    private void setResident(Resident resident) {
        if(resident == null){
            throw new IllegalArgumentException("resident is required");
        }
        this.resident = resident;
        resident.addReservedRoom(this);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if(startDate == null){
            throw new IllegalArgumentException("startDate is required");
        }
        if(startDate.isBefore(LocalDate.parse("2000-01-01"))){
            throw new IllegalArgumentException("Date should be after 01.01.2000");
        }
        if(startDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Date should be from the future");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    //custom constraint
    public void setEndDate(LocalDate endDate) {
        if(startDate == null){
            throw new IllegalArgumentException("end Date is required");
        }
        if(!endDate.isAfter(startDate)){
            throw new IllegalArgumentException("end Date should be after start date");
        }
        long resultDays = ChronoUnit.DAYS.between(startDate, endDate);
        LocalDate tmpDate = LocalDate.of(0, 1, 1).plusDays(resultDays - 1);

        String[] splitedDate = tmpDate.toString().split("-");
        int years = Integer.parseInt(splitedDate[0]);
        int months = Integer.parseInt(splitedDate[1]);
        int days = Integer.parseInt(splitedDate[2]);
        int reservedPeriodDays = years * 365 + months * 28 + days;

        if(reservedPeriodDays < 91){
            throw new IllegalArgumentException("can't reserve room to period < 3 months");
        }

        this.endDate = endDate;
    }

    public float getRent() {
        return rent;
    }

    public void setRent(float rent) {
        if(rent < 0){
            throw new IllegalArgumentException("rent should be positive");
        }
        this.rent = rent;
    }

    public void remove(){
        //should remove all references
        if(this.room != null) {
            Room tmpRoom = this.room;
            this.room = null;
            tmpRoom.removeReservedRoom(this);
        }
        if(this.resident != null) {
            Resident tmpRes = this.resident;
            this.resident = null;
            tmpRes.removeReservedRoom(this);
        }

//        this.startDate = null;
//        this.endDate = null;
//        this.rent = 0;
        System.out.println("References deleted");

        //remove resRoom from class extent
        removeFromExtent();
    }

    @Override
    public String toString() {
        if(this.room == null && this.resident == null)
            return null;
        return "ReservedRoom{" +
                "room=" + room.getRoomNumber() +
                ", resident=" + resident.getOwner().getName() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", rent=" + rent +
                '}';
    }
}
