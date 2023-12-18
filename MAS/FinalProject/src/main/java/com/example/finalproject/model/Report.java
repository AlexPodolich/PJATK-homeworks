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
 * This is a Report Class
 * It has a basic association One to Many(with Class Resident)
 * @see Resident
 */
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Description is mandatory")
    @Size(min = 2, max = 100)
    private String reportDescription;

    @NotBlank(message = "Room is mandatory")
    @Size(min = 2, max = 100)
    private String reportedRoom;

    /**
     * basic association One to Many(with Class Resident)
     * @see Resident
     */
    @ManyToOne
    @JoinColumn(name = "resident_id")
    @EqualsAndHashCode.Exclude
    private Resident resident;
}
