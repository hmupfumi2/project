package com.icap.icap.commons.utilities.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static com.icap.icap.commons.utilities.validations.Adult18LocalDateTimeValidator.ZIM_ADULT_AGE;


@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(Adult18.List.class)
@Constraint(validatedBy = {Adult18LocalDateValidator.class, Adult18DateValidator.class, Adult18LocalDateTimeValidator.class})
public @interface Adult18 {

    String message() default "Age should be at least " + ZIM_ADULT_AGE;

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
            ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {

        Adult18[] value();

    }

}
