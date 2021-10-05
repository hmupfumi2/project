package com.codevirtus.membershipsystem.auditlibrary.annotations;

import com.codevirtus.membershipsystem.auditlibrary.audit.ActionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Wilson Chiviti
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

    ActionType type();

}
