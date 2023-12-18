package Project.Resident;

import Project.ObjectPlus;
import Project.Person;
import Project.ReservedRoom;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Resident extends ObjectPlus {
    private Person owner;
    private String residentPhoneNumber;
    private String residentEmail;
    private static Pattern validEmailRegex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static Pattern validPhoneRegex = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$", Pattern.CASE_INSENSITIVE);
    private Set<ReservedRoom> reservedRooms = new HashSet<>();
    private Set<Report> reports = new HashSet<>();

    //subset
    private Set<SectionClub> memberOf = new HashSet<>();
    private Set<SectionClub> regulates = new HashSet<>();

    //XOR
    private Set<Document> documents = new HashSet<>();
    private ResidencePermit residencePermit;

    public Resident(Person owner, String residentPhoneNumber, String residentEmail) {
        super();
        setOwner(owner);
        setResidentPhoneNumber(residentPhoneNumber);
        setResidentEmail(residentEmail);

        addToExtent();
    }

    public Resident(Person owner, String residentPhoneNumber) {
        super();
        setOwner(owner);
        setResidentPhoneNumber(residentPhoneNumber);

        addToExtent();
    }

    public Person getOwner() {
        return owner;
    }

    private void setOwner(Person owner) {
        if(owner == null){
            throw new IllegalArgumentException("owner should not be null");
        }
        this.owner = owner;
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

    // UNIQUE
    public void setResidentEmail(String residentEmail) {
        if(residentEmail != null){
            if(residentEmail.isBlank()){
                throw new IllegalArgumentException("Resident Email should not be blank");
            }
            //email validation
            if(!validEmailRegex.matcher(residentEmail).matches()){
                throw new IllegalArgumentException("You should provide proper email");
            }
            if(residentEmail.equals(this.residentEmail)){
                return;
            }
            List<Resident> extent = getExtent(Resident.class);
            boolean checkForUnique = extent.stream().anyMatch(res -> res != this && res.residentEmail.equals(residentEmail));
            if(checkForUnique){
                throw new IllegalArgumentException("residentEmail must be unique");
            }
        }
        this.residentEmail = residentEmail;
    }

    public Set<ReservedRoom> getReservedRooms() {
        return Collections.unmodifiableSet(reservedRooms);
    }

    public void addReservedRoom(ReservedRoom reservedRoom){
        if(reservedRoom == null){
            throw new IllegalArgumentException("reservedRoom is required");
        }
        if(reservedRoom.getResident() != this){
            //not consistent
            throw new IllegalArgumentException("not allow to change reservedRoom");
        }
        reservedRooms.add(reservedRoom);
    }

    public void removeReservedRoom(ReservedRoom reservedRoom){
        if(reservedRoom == null){
            throw new IllegalArgumentException("ReservedRoom doesn't exist");
        }
        if(!this.reservedRooms.contains(reservedRoom)){
            return;
        }
        reservedRooms.remove(reservedRoom);
        reservedRoom.remove();
    }

    public void addReport(Report report){
        if(report == null){
            throw new IllegalArgumentException("report is required");
        }
        if(report.getReporter() != this){
            throw new IllegalArgumentException("wrong reporter");
        }
        if(!reports.contains(report)){
            this.reports.add(report);
            report.setReporter(this);
        }
    }

    public void removeReport(Report report){
        if(report == null){
            throw new IllegalArgumentException("report is required");
        }
        if(reports.contains(report)){
            reports.remove(report);
            report.removeFromExtent();
        }
    }

    public Set<Report> getReports(){
        return Collections.unmodifiableSet(reports);
    }

    public void addDocument(Document document){
        if(residencePermit != null){
            throw new IllegalArgumentException("you can't add document, when you have residence permit");
        }
        if(document == null){
            throw new IllegalArgumentException("document is required");
        }
        documents.add(document);
    }

    public void removeDocument(Document document){
        if(residencePermit != null){
            throw new IllegalArgumentException("exception xor constraint violation");
        }
        if(document == null){
            throw new IllegalArgumentException("document is required");
        }
        if(documents.contains(document)){
            documents.remove(document);
            document.removeFromExtent();
        }
    }

    public Set<Document> getDocuments() {
        return Collections.unmodifiableSet(documents);
    }

    public ResidencePermit getResidencePermit() {
        return residencePermit;
    }

    public void setResidencePermit(ResidencePermit residencePermit) {
        if(!documents.isEmpty()){
            throw new IllegalArgumentException("you can't set residence permit, when you have documents");
        }
        if(residencePermit == null){
            throw new IllegalArgumentException("residencePermit is required");
        }
        this.residencePermit = residencePermit;
    }

    public void removeResidencePermit(){
        if(residencePermit != null && documents.isEmpty()){
            residencePermit.removeFromExtent();
            residencePermit = null;
        }
    }

    //subset
    public List<SectionClub> getMemberOf() {
        List <SectionClub> workingTeams = memberOf.stream().sorted(Comparator.comparing(SectionClub::getActivityName, String.CASE_INSENSITIVE_ORDER)).collect(Collectors.toList());
        return Collections.unmodifiableList(workingTeams);
    }

    public void addMemberOf(SectionClub sectionClub){
        if(sectionClub == null){
            throw new IllegalArgumentException("sectionClub is required");
        }
        if(sectionClub.getConsistOf().size() >= sectionClub.getMaxNumberOfPeople()){
            throw new IllegalArgumentException("sectionClub is full");
        }

        memberOf.add(sectionClub);
        if(sectionClub.getConsistOf().contains(this)){
            return;
        }
        sectionClub.addHasMembers(this);
    }

    public void removeMemberOf(SectionClub sectionClub){
        if(this.regulates.contains(sectionClub)){
            //throw exception
            throw new IllegalArgumentException("you can't remove member when ones is a regulator of a section club");
        }
        memberOf.remove(sectionClub);
        if(!sectionClub.getConsistOf().contains(this)){
            return;
        }
        sectionClub.removeHasMembers(this);
    }

    public void addRegulates(SectionClub sectionClub){
        if(sectionClub == null){
            throw new IllegalArgumentException("sectionClub is required");
        }
        if(sectionClub.getRegulatedBy().size() >= sectionClub.getMaxNumberOfPeople()){
            throw new IllegalArgumentException("sectionClub is full");
        }
        //check for subset
        if(!this.memberOf.contains(sectionClub)){
            //not valid
            throw new IllegalArgumentException("resident must be member of a section club");
        }
        regulates.add(sectionClub);
        if(sectionClub.getRegulatedBy().contains(this)){
            return;
        }
        sectionClub.addRegulatedBy(this);
    }

    public void removeRegulates(SectionClub sectionClub){
        if(sectionClub == null){
            throw new IllegalArgumentException("sectionClub is required");
        }
        if(!this.regulates.contains(sectionClub)){
            return;
        }
        regulates.remove(sectionClub);
        if(!sectionClub.getRegulatedBy().contains(this)){
            return;
        }
        sectionClub.removeRegulatedBy(this);
    }

    public Set<SectionClub> getRegulates() {
        return Collections.unmodifiableSet(regulates);
    }


    @Override
    public void removeFromExtent() {
        super.removeFromExtent();

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
        return "Project.Resident.Resident{" +
                ", residentPhoneNumber='" + residentPhoneNumber + '\'' +
                ", residentEmail='" + residentEmail + '\'' +
                ", reservedRooms=" + reservedRooms +
                ", reports=" + reports +
                '}';
    }


}
