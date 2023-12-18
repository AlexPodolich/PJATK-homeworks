package com.example.finalproject.model;
import com.example.finalproject.model.model_enum.PersonHealth;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
/**
 * This is an abstract class Person
 * It has Multi-aspect Inheritance
 * First aspect: Employee, Resident
 * @see Employee
 * @see Resident
 * Second aspect: Healthy, Disabled(enum PersonHealth)
 * @see PersonHealth
 */
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "name is mandatory")
    @Size(min = 2, max = 100)
    private String name;
    @NotBlank(message = "surname is mandatory")
    @Size(min = 2, max = 100)
    private String surname;
    /**
     * attribute health for multi-aspect
     */
    @Enumerated
    @Column(nullable = false,updatable = false)
    private PersonHealth health;
    /**
     * attribute medicalCard in case if health = HEALTHY
     */
    @Size(min = 2, max = 100)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String medicalCard;

    /**
     * attribute listOfDemands in case if health = DISABLED
     */
    @ElementCollection
    @CollectionTable(name = "list_of_demands", joinColumns = @JoinColumn(name = "person_id"))
    @Builder.Default
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Set<String> listOfDemands = new HashSet<>();

    /**
     * constructor for multi-aspect inheritance
     */
    public Person(PersonHealth health, String name, String surname, String medicalCard, String demand) {
        setName(name);
        setSurname(surname);
        setHealth(health);

        if(health.equals(PersonHealth.HEALTHY)){
            setMedicalCard(medicalCard);
        }else {
            setMedicalCard(null);
        }

        if(health.equals(PersonHealth.DISABLED)){
            addDemand(demand);
        }else {
            listOfDemands = null;
        }
    }

    /**
     * method for checking PersonHealth to get list of demands
     */
    public Set<String> getDemands(){
        if (!health.equals(PersonHealth.HEALTHY)) {
            throw new IllegalArgumentException("You are not allowed to do it");
        }
        return listOfDemands;
    }

    /**
     * Method for adding demand to Set of demands
     */
    public Person addDemand(String demand){
        if(!health.equals(PersonHealth.DISABLED))
            throw new IllegalArgumentException("you are not allowed to perform this action");

        if(demand == null || demand.isBlank()){
            throw new IllegalArgumentException("demand can't be null");
        }
        listOfDemands.add(demand);
        return this;
    }

    /**
     * method for checking PersonHealth to get medical card
     */
    public String getMedicalCard(){
        if (!health.equals(PersonHealth.DISABLED)) {
            throw new IllegalArgumentException("You are not allowed to do it");
        }
        return medicalCard;
    }

    /**
     * Method for setting medical card
     */
    public Person setMedicalCard(String newMedicalCard) {
        if(!health.equals(PersonHealth.HEALTHY))
            throw new IllegalArgumentException("you are not allowed to perform this action");

        if(newMedicalCard == null || newMedicalCard.isBlank()){
            throw new IllegalArgumentException("medical Card can't be null");
        }
        this.medicalCard = newMedicalCard;
        return this;
    }
}
