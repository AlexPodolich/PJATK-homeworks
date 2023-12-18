package com.example.finalproject.model;

import com.example.finalproject.model.model_interface.IResident;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
/**
 * This is a WorkingResident Class that extends Employee class
 * @see Employee
 * and implements IResident interface
 * @see IResident
 * It is a class that represents Multi-inheritance
 */
public class WorkingResident extends Employee implements IResident {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Field is mandatory")
    @Min(value = 0, message = "discount For Living should not be less than 0!")
    @Max(value = 100, message = "discount For Living should not be more than 100!")
    private float discountForLiving;

    @NotBlank(message = "Field is mandatory")
    @Size(min = 7, max = 100)
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$")
    private String residentPhoneNumber;

    /**
     * optional attribute and unique constraint
     */
    @Size(min = 4, max = 100)
    @Email
    @Column(unique = true)
    @Builder.Default
    private String residentEmail = "N/A";

}
