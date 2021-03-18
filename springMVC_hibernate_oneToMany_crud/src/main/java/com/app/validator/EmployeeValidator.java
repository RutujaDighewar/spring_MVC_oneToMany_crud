package com.app.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.dto.EmployeeDto;
import com.app.entity.Employee;

public class EmployeeValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(EmployeeDto.class);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
	}

	
	
}
