package com.example.finalproject.model;
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
/**
 * This is a Room Class,
 * Room has connection Many to One(with Class Dormitory)
 * @see Dormitory
 * Room has association with an attribute(with Class Resident) implemented by class ReservedRoom
 * @see Resident
 * @see ReservedRoom
 */
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Field is mandatory")
    @Size(min = 2, max = 100)
    private String roomNumber;
    @Min(0)
    private float roomPrice;
    @NotBlank(message = "Field is mandatory")
    @Size(min = 2, max = 100)
    private String roomDescription;
    @Min(0)
    @Setter(AccessLevel.NONE)
    private float roomSize;
    /**
     * class attribute
     */
    private static float minRoomSize = 25;


    /**
     * This is an association Many to One(with Class Dormitory),
     * @see Dormitory
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "dormitory_id", nullable = false, updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private Dormitory dormitory;

    /**
     * association with an attribute Many to Many(with Class Resident) implemented by Class ReservedRoom
     * @see Room
     * @see ReservedRoom
     */
    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<ReservedRoom> reservedRooms = new HashSet<>();

    /**
     * setter for room size with validation(it must be > minRoomSize)
     */
    public Room setRoomSize(float roomSize) {
        if(roomSize < minRoomSize){
            throw new IllegalArgumentException("Room Size must be > min Room Size");
        }
        this.roomSize = roomSize;
        return this;
    }
}
