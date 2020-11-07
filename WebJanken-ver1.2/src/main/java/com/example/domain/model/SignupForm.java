package com.example.domain.model;

import lombok.Data;

@Data
public class SignupForm {
	private String userId;
	private String password;
	private String userName;
	private boolean gender;
}
