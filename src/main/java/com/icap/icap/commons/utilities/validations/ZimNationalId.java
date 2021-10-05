package com.icap.icap.commons.utilities.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;



@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(ZimNationalId.List.class)
@Constraint(validatedBy = {ZimNationalIdValidator.class})
public @interface ZimNationalId {

    String message() default "A valid Zimbabwe national id should be provided";

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
            ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {

        ZimNationalId[] value();

    }

}
