package Project.Resident;

import Project.ObjectPlus;
import Project.Room;

public class Report extends ObjectPlus {
    private long id;
    private String reportDescription;
    private String reportedRoom;
    private Resident reporter;

    public Report(long id, String reportDescription, String reportedRoom, Resident reporter) {
        setId(id);
        setReportDescription(reportDescription);
        setReportedRoom(reportedRoom);
        setReporter(reporter);

        addToExtent();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(getExtent(Report.class).stream().anyMatch(m -> m.id == id)){
            throw new IllegalArgumentException("id must be unique");
        }
        if (id > 0){
            this.id = id;
        }else{
            throw new IllegalArgumentException("id must be a positive value");
        }
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        if(reportDescription == null || reportDescription.isBlank()){
            throw new IllegalArgumentException("report Description is required");
        }
        this.reportDescription = reportDescription;
    }

    public String getReportedRoom() {
        return reportedRoom;
    }

    public void setReportedRoom(String reportedRoom) {
        if(reportedRoom == null || reportedRoom.isBlank()){
            throw new IllegalArgumentException("reported Project.Project.Room is required");
        }
        if(getExtent(Room.class).stream().noneMatch(m -> m.getRoomNumber().equals(reportedRoom))){
            throw new IllegalArgumentException("room doesn't exist");
        }
        this.reportedRoom = reportedRoom;
    }

    public Resident getReporter() {
        return reporter;
    }

    public void setReporter(Resident reporter) {
        //check if reporter is not the same as the old one
        if(this.reporter != reporter){
            if (this.reporter == null && reporter != null){
                //create new association
                this.reporter = reporter;
                reporter.addReport(this);
            }else if(this.reporter != null && reporter == null) {
                //remove association
                Resident tmp = this.reporter;
                this.reporter = null;
                tmp.removeReport(this);
            }else if(this.reporter != null && reporter != null){
                //change to another association
                Resident tmp = this.reporter;
                tmp.removeReport(this);
                this.reporter = reporter;
                reporter.addReport(this);
            }
        }
    }

    @Override
    public void removeFromExtent() {
        setReporter(null);
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "Project.Project.Resident.Report{" +
                "id=" + id +
                ", reportDescription='" + reportDescription + '\'' +
                ", reportedRoom='" + reportedRoom + '\'' +
                ", reporter=" + reporter.getOwner().getName() +
                '}';
    }
}
