package com.icap.icap.commons.utilities.exceptions;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomConstraintViolationException extends ConstraintViolationException {

    public CustomConstraintViolationException(Set<? extends ConstraintViolation<?>> constraintViolations) {

        super(toString(constraintViolations), constraintViolations);

    }

    private static String toString(Set<? extends ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream()
                .map( cv -> cv == null ? "null" : cv.getMessage() )
                .collect( Collectors.joining( ", \n" ) );
    }
}
