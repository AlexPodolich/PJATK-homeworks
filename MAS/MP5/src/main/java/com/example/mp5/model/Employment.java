package com.example.mp5.model;

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
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Past
    private LocalDate startDate;
    private LocalDate endDate;
    @Min(0)
    private float monthlySalary;
    @ElementCollection
    @CollectionTable(name = "penalty", joinColumns = @JoinColumn(name = "employment_id"))
    @Builder.Default
    private Set<String> penalty = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "dormitory_id", nullable = false)
    @NotNull
    @Setter(AccessLevel.PRIVATE)
    private Dormitory dormitory;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @NotNull
    @Setter(AccessLevel.PRIVATE)
    private Employee employee;
}
