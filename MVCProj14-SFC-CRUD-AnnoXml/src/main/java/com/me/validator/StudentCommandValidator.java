package com.me.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.domain.StudentCommand;

@Component("validator")
public class StudentCommandValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.isAssignableFrom(StudentCommand.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StudentCommand command = (StudentCommand) target;
		System.out.println(command);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "age.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required");

		if (command.getAge() != null) {
			if (command.getAge() > 100) {
				errors.rejectValue("age", "max.age");
			}
		}

		if (command.getAddress() != null) {
			if (command.getAddress().equalsIgnoreCase("delhi")) {
				errors.rejectValue("address", "restriction.address");
			}
		}

	}

}
