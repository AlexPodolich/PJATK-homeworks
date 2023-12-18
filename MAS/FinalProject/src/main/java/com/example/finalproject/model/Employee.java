package com.example.finalproject.model;
import com.example.finalproject.model.model_enum.EmployeeType;
import com.example.finalproject.model.model_enum.PersonHealth;
import com.example.finalproject.model.model_interface.IAdministrator;
import com.example.finalproject.model.model_interface.ICleaningStuff;
import com.example.finalproject.model.model_interface.ISecurity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
/**
 * This is a class Employee that extends Class Person,
 * @see Person
 * and implements interfaces IAdministrator, ISecurity, ICleaningStuff
 * @see IAdministrator
 * @see ISecurity
 * @see ICleaningStuff
 * Employee class has Dynamic and Overlapping Inheritance
 * Employee has association with an attribute(with class Dormitory) implemented by class Employment
 * @see Dormitory
 * @see Employment
 */
public class Employee extends Person implements IAdministrator, ISecurity, ICleaningStuff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Dynamic inheritance - Employee type
     * @see EmployeeType
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = EmployeeType.class)
    @Column(nullable = false)
    private Set<EmployeeType> empType;

    /**
     * Multi-value attribute
     */
    @ElementCollection
    @CollectionTable(name = "working_phone_number", joinColumns = @JoinColumn(name = "employee_id"))
    @Builder.Default
    private Set<String> workingPhoneNumbers = new HashSet<>();

    /**
     * attribute for Type ADMIN in dynamic inheritance
     */
    @ElementCollection
    @CollectionTable(name = "admin_responsibility", joinColumns = @JoinColumn(name = "employee_id"))
    @Builder.Default
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Set<String> adminResponsibilities = new HashSet<>();

    /**
     * attribute for Type SECURITY in dynamic inheritance
     */
    @Size(min = 2, max = 100)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String patrolArea;

    /**
     * attribute for Type CLEANING_STUFF in dynamic inheritance
     */
    @Size(min = 2, max = 100)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String cleaningArea;

    /**
     * association with an attribute Many to Many(with Class Dormitory) implemented by Class Employment
     * @see Dormitory
     * @see Employment
     */
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Employment> employments = new HashSet<>();


    /**
     * Constructor for Overlapping Inheritance
     */
    public Employee(PersonHealth health, String name, String surname, String medicalCard, String demand,
                    Set<EmployeeType> types, String workingPhoneNumber,
                    String adminResponsibility,
                    String patrolArea,
                    String cleaningArea){
        super(health, name, surname, medicalCard, demand);
        setEmpType(types);

        addWorkingPhoneNumber(workingPhoneNumber);

        if(types.contains(EmployeeType.ADMIN)){
            addAdminResponsibility(adminResponsibility);
        }else{
            adminResponsibilities = null;
        }

        if(types.contains(EmployeeType.SECURITY)){
            setPatrolArea(patrolArea);
        }else{
            setPatrolArea(null);
        }

        if(types.contains(EmployeeType.CLEANING_STUFF)){
            setCleaningArea(cleaningArea);
        }else{
            setCleaningArea(null);
        }
    }

    /**
     * Method for adding working phone number to Set of working phone numbers
     */
    public void addWorkingPhoneNumber(String phoneNum){
        if(phoneNum == null || phoneNum.isBlank()){
            throw new IllegalArgumentException("phoneNum is required");
        }
        //phone validation
        if(!Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$", Pattern.CASE_INSENSITIVE).matcher(phoneNum).matches()){
            throw new IllegalArgumentException("You should provide proper phone number");
        }
        workingPhoneNumbers.add(phoneNum);
    }

    /**
     * Method for adding admin responsibility to Set of working responsibilities for employee type ADMIN
     */
    public void addAdminResponsibility(String adminResponsibility){
        if(!empType.contains(EmployeeType.ADMIN)){
            throw new IllegalArgumentException("You are not allowed to do this");
        }
        Set<String> adminResponsibilities = new HashSet<>();
        adminResponsibilities.add(adminResponsibility);
    }

    /**
     * Method for removing admin responsibilities;
     */
    public void removeAdminResponsibilities(){
        if(!empType.contains(EmployeeType.ADMIN)){
            throw new IllegalArgumentException("You are not allowed to do this");
        }
        this.adminResponsibilities = null;
    }

    /**
     * Method for setting patrol area for employee type SECURITY
     */
    public void setPatrolArea(String patrolArea){
        if(!empType.contains(EmployeeType.SECURITY)){
            throw new IllegalArgumentException("You are not allowed to do this");
        }
        this.patrolArea = patrolArea;
    }

    /**
     * Method for setting cleaning area for employee type CLEANING_STUFF
     */
    public void setCleaningArea(String cleaningArea){
        if(!empType.contains(EmployeeType.CLEANING_STUFF)){
            throw new IllegalArgumentException("You are not allowed to do this");
        }
        this.cleaningArea = cleaningArea;
    }

    /**
     * Method for getting admin responsibilities for employee type ADMIN
     */
    public Set<String> getAdminResponsibilities(){
        if(!empType.contains(EmployeeType.ADMIN)){
            throw new IllegalArgumentException("You are not allowed to do this");
        }
        return adminResponsibilities;
    }

    /**
     * Method for getting patrol area for employee type SECURITY
     */
    public String getPatrolArea() {
        if(!empType.contains(EmployeeType.SECURITY)){
            throw new IllegalArgumentException("You are not allowed to do this");
        }
        return patrolArea;
    }

    /**
     * Method for getting cleaning area for employee type CLEANING_STUFF
     */
    public String getCleaningArea() {
        if(!empType.contains(EmployeeType.CLEANING_STUFF)){
            throw new IllegalArgumentException("You are not allowed to do this");
        }
        return cleaningArea;
    }
}
