package com.icap.icap.commons.utilities.validations;

import lombok.val;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

public class EcocashNumberValidator implements ConstraintValidator<EcocashNumber, String> {

    private static final int MAX_NUMBER_LENGTH = 12;

    private static final int MIN_NUMBER_LENGTH = 9;

    private static final List<String> econetNumberPrefixes = Arrays.asList("077", "078", "77", "78", "26377", "26378", "0026377", "0026378", "+26377", "+26378");

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {

        if (isNull(phoneNumber)) {
            return false;
        }

        val trimmedNumber = phoneNumber.trim()
                .replace("+", "")
                .replace(" ", "");

        val allNumbers = trimmedNumber.chars()
                .allMatch(Character::isDigit);

        if (!allNumbers) {
            return false;
        }

        val validPrefixes = econetNumberPrefixes.stream()
                .anyMatch(trimmedNumber::startsWith);

        if (!validPrefixes) {
            return false;
        }

        if (trimmedNumber.length() > MAX_NUMBER_LENGTH
                || trimmedNumber.length() < MIN_NUMBER_LENGTH) {
            return false;
        }

        return true;

    }
}
