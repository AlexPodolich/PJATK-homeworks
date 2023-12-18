package com.example.finalproject.validation;

import com.example.finalproject.model.Resident;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class XorValidation implements ConstraintValidator<Xor, Resident> {


    /**
     * overriding method isValid in order to check that resident cannot have association with Document and ResidencePermit at the same time
     */
    @Override
    public boolean isValid(Resident resident, ConstraintValidatorContext context) {
        if (resident == null) {
            return true ;
        }
        boolean hasDocuments = !resident.getDocuments().isEmpty();
        boolean hasResidencePermit = resident.getResidencePermit() != null;

        return hasDocuments ^ hasResidencePermit;
    }
}

