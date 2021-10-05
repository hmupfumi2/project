package com.icap.icap.commons.utilities.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Date;

import static java.util.Objects.isNull;


public class Adult18DateValidator implements ConstraintValidator<Adult18, Date> {

    static final int ZIM_ADULT_AGE = 18;

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {

        if(isNull(value)){
            return true;
        }

        return value.before(java.sql.Date.valueOf(LocalDate.now().minusYears(ZIM_ADULT_AGE)));

    }
}
