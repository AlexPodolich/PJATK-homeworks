package com.example.mp5.model;

import com.example.mp5.repository.DormitoryRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Dormitory{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Field is mandatory")
    @Size(min = 2, max = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    private DormitoryRating dormitoryRating;

    @NotBlank(message = "Field is mandatory")
    @Size(min = 2, max = 100)
    private String address;
    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Room> rooms = new HashSet<>();
    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Employment> employments = new HashSet<>();
}
