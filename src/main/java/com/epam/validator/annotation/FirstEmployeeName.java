package com.epam.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.epam.validator.FirstEmployeeNameValidator;

@Documented
@Constraint(validatedBy = FirstEmployeeNameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FirstEmployeeName {

	String message() default "{FirstEmployeeName}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
