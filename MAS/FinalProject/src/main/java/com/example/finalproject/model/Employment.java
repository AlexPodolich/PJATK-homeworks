package com.example.finalproject.model;
import com.example.finalproject.model.model_interface.IAdministrator;
import com.example.finalproject.model.model_interface.ICleaningStuff;
import com.example.finalproject.model.model_interface.ISecurity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * This is a class Employment,
 * Employment class uses in association with an attribute(between Classes Dormitory and Employee)
 * @see Dormitory
 * @see Employee
 */
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalDate startDate;

    /**
     * Optional attribute
     */
    @Builder.Default
    private LocalDate endDate = null;
    @Setter(AccessLevel.NONE)
    @Min(0)
    private float monthlySalary;

    /**
     * Derived attribute
     * @return actual salary of Employee for current employment
     */
    @Transient
    public double getActualSalary() {
        int numOfPenalties = penalties.size();
        float fineForPenalties = 100.0f * numOfPenalties;
        float actualSalary = monthlySalary - fineForPenalties;
        return actualSalary;
    }

    /**
     * Optional, multi value attribute
     */
    @ElementCollection
    @CollectionTable(name = "penalty", joinColumns = @JoinColumn(name = "employment_id"))
    @Builder.Default
    private Set<String> penalties = new HashSet<>();

    /**
     * connection Many to One(with Class Dormitory) for association with an attribute(between Classes Dormitory and Employee)
     * @see Dormitory
     */
    @ManyToOne
    @JoinColumn(name = "dormitory_id", nullable = false)
    @NotNull
    @Setter(AccessLevel.PRIVATE)
    private Dormitory dormitory;

    /**
     * connection Many to One(with Class Employee) for association with an attribute(between Classes Dormitory and Employee)
     * @see Employee
     */
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @NotNull
    @Setter(AccessLevel.PRIVATE)
    private Employee employee;


    /**
     * dynamic constraint
     */
    public Employment setMonthlySalary(Float monthlySalary) {
        if(monthlySalary != null && monthlySalary < 0){
            throw new IllegalArgumentException("monthly Salary must be positive");
        }
        if(monthlySalary < this.monthlySalary * 0.9) {
            throw new IllegalArgumentException("you cannot reduce monthly salary by 10% or more");
        }
        if(monthlySalary > this.monthlySalary * 1.25) {
            throw new IllegalArgumentException("you cannot increase monthly salary by 25% or more");
        }
        this.monthlySalary = monthlySalary;
        return this;
    }

}
