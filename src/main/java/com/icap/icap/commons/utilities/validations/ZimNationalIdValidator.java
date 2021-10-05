package com.icap.icap.commons.utilities.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;


public class ZimNationalIdValidator implements ConstraintValidator<ZimNationalId, String> {

    private static final String NATIONAL_ID_NUMBER_REGEX = "^[0-9]{2}-[0-9]{5,7}[A-Za-z][0-9]{2}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(isNull(value)){
            return true;
        }

        if(value.trim().isEmpty()){
            return false;
        }

        return Pattern.matches(NATIONAL_ID_NUMBER_REGEX, value);
    }
}
