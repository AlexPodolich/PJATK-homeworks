package com.example.finalproject.model;
import com.example.finalproject.model.model_enum.PersonHealth;
import com.example.finalproject.model.model_interface.IResident;
import com.example.finalproject.validation.Xor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@ToString
@Xor
/**
 * This is a Resident Class that extends Person Class
 * @see Person
 * and implements IResident interface
 * @see IResident
 * Resident has XOR constraint with classes Document, ResidencePermit
 * @see Document
 * @see ResidencePermit
 * Resident has basic association One to Many(with Class Report)
 * @see Report
 * Resident has association with an attribute(with Class Room) implemented by class ReservedRoom
 * @see Room
 * @see ReservedRoom
 */
public class Resident extends Person implements IResident {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Field is mandatory")
    @Size(min = 7, max = 100)
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$")
    private String residentPhoneNumber;

    /**
     * optional attribute and unique constraint
     */
    @Size(min = 4, max = 100)
    @Email
    @Column(unique = true)
    @Builder.Default
    private String residentEmail = "N/A";

    /**
     * basic association One to Many(with Class Report)
     * @see Report
     */
    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Report> reports = new HashSet<>();

    /**
     * association with an attribute Many to Many(with Class Room) implemented by Class ReservedRoom
     * @see Room
     * @see ReservedRoom
     */
    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<ReservedRoom> reservedRooms = new HashSet<>();

    /**
     * XOR constraint, composition association One to Many(with Class Document)
     * @see Document
     */
    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @Setter(AccessLevel.NONE)
    private Set<Document> documents = new HashSet<>();

    /**
     * XOR constraint, composition association One to One(with Class ResidencePermit)
     * @see ResidencePermit
     */
    @OneToOne(mappedBy = "resident", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    private ResidencePermit residencePermit;

    /**
     * Constructor with all attributes
     */
    public Resident(PersonHealth health, String name, String surname, String medicalCard, String demand,
                    String residentPhoneNumber, String residentEmail) {
        super(health, name, surname, medicalCard, demand);
        setResidentPhoneNumber(residentPhoneNumber);
        setResidentEmail(residentEmail);
    }

    /**
     * Constructor without optional attribute(residentEmail)
     */
    public Resident(PersonHealth health, String name, String surname, String medicalCard, String demand,
                    String residentPhoneNumber) {
        super(health, name, surname, medicalCard, demand);
        setResidentPhoneNumber(residentPhoneNumber);
    }

    /**
     * method to check whether Resident has residence permit, if he doesn't have, add Document to documents
     */
    public void addDocument(Document document){
        if(residencePermit != null){
            throw new IllegalArgumentException("you can't add document, when you have residence permit");
        }
        if(document == null){
            throw new IllegalArgumentException("document is required");
        }
        documents.add(document);
    }

    /**
     * setter for ResidencePermit where it checks whether Resident has documents, if he doesn't have, set value
     */
    public void setResidencePermit(ResidencePermit residencePermit) {
        if(!documents.isEmpty()){
            throw new IllegalArgumentException("you can't set residence permit, when you have documents");
        }
        if(residencePermit == null){
            throw new IllegalArgumentException("residencePermit is required");
        }
        this.residencePermit = residencePermit;
    }

}
