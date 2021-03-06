package com.epam.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.epam.model.Employee;

public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Employee.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"name.required");
	}

}
