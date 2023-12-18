package com.example.finalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
/**
 * This is a class Address that consists of two attributes(street and number),
 * this is a complex attribute for Class Dormitory
 * @see Dormitory
 */
public class Address {
    @NotBlank(message = "street is mandatory")
    @Column(nullable = false)
    @Size(min = 2, max=255)
    private String street;
    @Min(0)
    private Integer number;
}
