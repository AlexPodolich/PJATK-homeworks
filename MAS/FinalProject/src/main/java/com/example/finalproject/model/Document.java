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
 * This is a class Document,
 * this is one of classes for XOR constraint for Class Resident
 * @see Resident
 * also for XOR constraint used ResidencePermit Class
 * @see ResidencePermit
 */
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long documentId;

    @NotBlank(message = "name is mandatory")
    @Column(nullable = false)
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "content is mandatory")
    @Column(nullable = false)
    @Size(min = 2, max = 250)
    private String content;


    /**
     * This is an association many to one(with Class Resident),
     * @see Resident
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "resident_id", nullable = false, updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private Resident resident;
}
