package com.example.mp5.model;

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
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Field is mandatory")
    @Size(min = 2, max = 100)
    private String reportDescription;

    @NotBlank(message = "Field is mandatory")
    @Size(min = 2, max = 100)
    private String reportedRoom;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    @EqualsAndHashCode.Exclude
    private Resident resident;
}
