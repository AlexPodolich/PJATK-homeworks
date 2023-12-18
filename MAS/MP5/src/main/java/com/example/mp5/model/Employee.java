package com.example.mp5.model;

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
public class Employee extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "working_phone_number", joinColumns = @JoinColumn(name = "employee_id"))
    @Builder.Default
    private Set<String> workingPhoneNumbers = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Employment> employments = new HashSet<>();
}
