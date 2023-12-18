package com.example.finalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
/**
 * This is a ResidencePermit Class,
 * this is one of classes for XOR constraint for Class Resident
 * @see Resident
 * also for XOR constraint used Document Class
 * @see Document
 */
public class ResidencePermit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long residencePermitId;

    @NotBlank(message = "type is mandatory")
    @Size(min = 2, max = 100)
    private String type;

    @NotBlank(message = "status is mandatory")
    @Size(min = 2, max = 250)
    private String status;

    /**
     * This is a composition connection one to one(with Class Resident),
     * @see Resident
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "resident_id", nullable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.PRIVATE)
    private Resident resident;
}
