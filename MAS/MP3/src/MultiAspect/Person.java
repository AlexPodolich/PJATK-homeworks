package MultiAspect;

import Project.ObjectPlus;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Person extends ObjectPlus {
    private final PersonHealth health;
    private long id;
    private String name;
    private String surname;
    private Set<String> listOfDemands = new HashSet<>();
    private String medicalCard;

    public Person(PersonHealth health, long id, String name, String surname, String medicalCard, String demand) {
        setId(id);
        setName(name);
        setSurname(surname);

        if(health == null){
            throw new IllegalArgumentException("health can't be null");
        }

        this.health = health;

        if(health.equals(PersonHealth.HEALTHY)){
            setMedicalCard(medicalCard);
        }else {
            medicalCard = null;
        }

        if(health.equals(PersonHealth.DISABLED)){
            addDemand(demand);
        }else {
            listOfDemands = null;
        }

        addToExtent();
    }

    public PersonHealth getHealth() {
        return health;
    }

    public void addDemand(String demand){
        if(!health.equals(PersonHealth.DISABLED))
            throw new IllegalArgumentException("you are not allowed to perform this action");

        if(demand == null || demand.isBlank()){
            throw new IllegalArgumentException("demand can't be null");
        }
        listOfDemands.add(demand);
    }

    public Set<String> getListOfDemands() {
        if(listOfDemands == null){
            throw new IllegalArgumentException("You can't do it");
        }
        return Collections.unmodifiableSet(listOfDemands);
    }

    public String getMedicalCard() {
        if(medicalCard == null){
            throw new IllegalArgumentException("You can't do it");
        }
        return medicalCard;
    }

    public void setMedicalCard(String newMedicalCard) {
        if(!health.equals(PersonHealth.HEALTHY))
            throw new IllegalArgumentException("you are not allowed to perform this action");

        if(newMedicalCard == null || newMedicalCard.isBlank()){
            throw new IllegalArgumentException("medical Card can't be null");
        }
        this.medicalCard = newMedicalCard;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        if(getExtent(Person.class).stream()
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname == null || surname.isBlank()){
            throw new IllegalArgumentException("surname is required");
        }
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "health=" + health +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}