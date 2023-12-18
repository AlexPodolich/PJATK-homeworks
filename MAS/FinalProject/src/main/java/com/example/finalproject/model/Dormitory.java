package com.example.finalproject.model;

import com.example.finalproject.model.model_enum.DormitoryRating;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
/**
 * This is a class Dormitory,
 * Dormitory has Embeddable Class Address
 * @see Address
 * Dormitory has connection Many to One(with Class Owner)
 * @see Owner
 * Dormitory has composition connection One to Many(with Class Room)
 * @see Room
 * Dormitory has association with an attribute(with class Employee) implemented by class Employment
 * @see Employee
 * @see Employment
 */
public class Dormitory{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "name is mandatory")
    @Column(nullable = false)
    @Size(min = 2, max = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private DormitoryRating dormitoryRating;

    /**
     * complex attribute
     */
    @NotNull
    @Column(nullable = false)
    @Embedded
    private Address address;

    /**
     * composition association One to Many(with Class Room)
     * @see Room
     */
    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Room> rooms = new HashSet<>();

    /**
     * association with an attribute Many to Many(with Class Employee) implemented by Class Employment
     * @see Employee
     * @see Employment
     */
    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Employment> employments = new HashSet<>();

    /**
     * basic association Many to One(with Class Owner)
     * @see Owner
     */
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @EqualsAndHashCode.Exclude
    private Owner owner;
}
