package com.example.finalproject.model;
import com.example.finalproject.model.model_enum.ReservedRoomState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * This is a Class ReservedRoom,
 * ReservedRoom class uses in association with an attribute(between Classes Room and Resident)
 * @see Room
 * @see Resident
 */
public class ReservedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Future
    private LocalDate startDate;
    @NotNull
    @Future
    @Setter(AccessLevel.NONE)
    private LocalDate endDate;
    @Min(0)
    private float rent;
    /**
     * connection Many to One(with Class Room) for association with an attribute(between Classes Room and Resident)
     * @see Room
     */
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @NotNull
    private Room room;
    /**
     * connection Many to One(with Class Resident) for association with an attribute(between Classes Room and Resident)
     * @see Resident
     */
    @ManyToOne
    @JoinColumn(name = "resident_id", nullable = false)
    @NotNull
    private Resident resident;

    /**
     * attribute for tracking state of the reservation
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservedRoomState state;


    /**
     * custom constraint
     */
    public ReservedRoom setEndDate(LocalDate endDate) {
        if(startDate == null){
            throw new IllegalArgumentException("end Date is required");
        }
        if(!endDate.isAfter(startDate)){
            throw new IllegalArgumentException("end Date should be after start date");
        }
        long resultDays = ChronoUnit.DAYS.between(startDate, endDate);
        LocalDate tmpDate = LocalDate.of(0, 1, 1).plusDays(resultDays - 1);

        String[] splitedDate = tmpDate.toString().split("-");
        int years = Integer.parseInt(splitedDate[0]);
        int months = Integer.parseInt(splitedDate[1]);
        int days = Integer.parseInt(splitedDate[2]);
        int reservedPeriodDays = years * 365 + months * 28 + days;

        if(reservedPeriodDays < 91){
            throw new IllegalArgumentException("can't reserve room to period < 90 days");
        }

        this.endDate = endDate;
        return this;
    }
}
