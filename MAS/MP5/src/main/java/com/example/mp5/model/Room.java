package com.example.mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dormitory_id", nullable = false, updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private Dormitory dormitory;
    @NotBlank(message = "Field is mandatory")
    @Size(min = 2, max = 100)
    private String roomNumber;
    @Min(0)
    private float roomPrice;
    @NotBlank(message = "Field is mandatory")
    @Size(min = 2, max = 100)
    private String roomDescription;
    @Min(0)
    private float roomSize;
    @Min(0)
    private static float minRoomSize = 25;

    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<ReservedRoom> reservedRooms = new HashSet<>();
}
