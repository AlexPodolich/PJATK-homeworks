package com.example.finalproject.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
/**
 * This is a class Owner that extends class Person
 * @see Person
 * Owner has basic association One to Many(with Class Dormitory)
 * @see Dormitory
 */
public class Owner extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * basic association One to Many(with Class Dormitory)
     * @see Dormitory
     */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Dormitory> dormitories = new HashSet<>();
}
