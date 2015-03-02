package com.epam.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.epam.validator.annotation.FirstEmployeeName;

public class FirstEmployeeNameValidator implements
		ConstraintValidator<FirstEmployeeName, String> {

	@Override
	public void initialize(FirstEmployeeName constraintAnnotation) {
	}

	@Override
	public boolean isValid(String firstName, ConstraintValidatorContext context) {
		if (firstName == null) {
			return false;
		}
		if (firstName.matches("/^[a-z ,.'-]+$/i")) {
			return true;
		} else
			return false;
	}

}
