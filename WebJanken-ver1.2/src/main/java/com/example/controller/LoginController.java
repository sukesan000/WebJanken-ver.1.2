package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	@GetMapping(value="/html/login")
	public String GetLogin()
	{
		return "html/login";
	}
	
	@PostMapping(value="/html/login")
	public String postLogin(Model model) {
		return "/html/login";
	}
}
