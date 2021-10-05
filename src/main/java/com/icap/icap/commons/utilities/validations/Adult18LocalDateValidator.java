package com.icap.icap.commons.utilities.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static java.util.Objects.isNull;


public class Adult18LocalDateValidator implements ConstraintValidator<Adult18, LocalDate> {

    static final int ZIM_ADULT_AGE = 18;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {

        if(isNull(value)){
            return true;
        }

        return value.isBefore(LocalDate.now().minusYears(ZIM_ADULT_AGE));

    }
}
