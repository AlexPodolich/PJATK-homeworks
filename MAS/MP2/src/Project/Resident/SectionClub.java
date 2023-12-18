package Project.Resident;

import Project.Employee.Employee;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SectionClub {
    private long id;
    private String activityName;
    private int maxNumberOfPeople;

    //subset
    private Set<Resident> hasMembers = new HashSet<>();
    private Set<Resident> regulatedBy = new HashSet<>();

    public SectionClub(long id, String activityName, int maxNumberOfPeople) {
        setId(id);
        setActivityName(activityName);
        setMaxNumberOfPeople(maxNumberOfPeople);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(id < 0){
            throw new IllegalArgumentException("id must be positive");
        }
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        if(activityName == null || activityName.isBlank()){
            throw new IllegalArgumentException("activityName is required");
        }
        this.activityName = activityName;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(int maxNumberOfPeople) {
        if(maxNumberOfPeople < 0){
            throw new IllegalArgumentException("id must be positive");
        }
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public Set<Resident> getConsistOf() {
        return Collections.unmodifiableSet(hasMembers);
    }

    public void addHasMembers(Resident resident){
        if(resident == null){
            throw new IllegalArgumentException("resident is required");
        }
        if(hasMembers.size() >= maxNumberOfPeople){
            throw new IllegalArgumentException("sectionClub is full");
        }
        hasMembers.add(resident);
        if(resident.getMemberOf().contains(this)){
            return;
        }
        resident.addMemberOf(this);
    }

    public void removeHasMembers(Resident resident){
        if(this.regulatedBy.contains(resident)){
            //throw exception
            throw new IllegalArgumentException("you can't remove member when ones is a regulator of a section club");
        }
        hasMembers.remove(resident);
        if(!resident.getMemberOf().contains(this)){
            return;
        }
        resident.removeMemberOf(this);
    }

    public void addRegulatedBy(Resident resident){
        if(resident == null){
            throw new IllegalArgumentException("resident is required");
        }
        if(regulatedBy.size() >= maxNumberOfPeople){
            throw new IllegalArgumentException("sectionClub is full");
        }
        //check for subset
        if(!this.hasMembers.contains(resident)){
            //not valid
            throw new IllegalArgumentException("Club must have this resident as member to add him as regulator");
        }
        regulatedBy.add(resident);
        if(resident.getRegulates().contains(this)){
            return;
        }
        resident.addRegulates(this);
    }


    public void removeRegulatedBy(Resident resident){
        if(resident == null){
            throw new IllegalArgumentException("resident is required");
        }
        if(!this.regulatedBy.contains(resident)){
            return;
        }
        regulatedBy.remove(resident);
        if(!resident.getRegulates().contains(this)){
            return;
        }
        resident.removeRegulates(this);
    }

    public Set<Resident> getRegulatedBy() {
        return Collections.unmodifiableSet(regulatedBy);
    }

    @Override
    public String toString() {
        return "SectionClub{" +
                "id=" + id +
                ", activityName='" + activityName + '\'' +
                ", maxNumberOfPeople=" + maxNumberOfPeople +
                '}';
    }
}
