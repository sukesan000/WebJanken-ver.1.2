package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	@GetMapping("/html/login")
	public String GetLogin(Model model){
		return "html/login";
	}
	
	@PostMapping("/html/login")
	public String postLogin(Model model) {
		return "redirect:/html/battle";
	}
}
