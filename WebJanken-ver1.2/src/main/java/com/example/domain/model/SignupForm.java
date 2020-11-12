package com.example.domain.model;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignupForm {
	@NotBlank
	@Email
	private String userId;
	@NotBlank
	@Length(min=3, max=10)
	private String password;
	@NotBlank
	private String userName;
	@AssertFalse
	private boolean gender;
}
