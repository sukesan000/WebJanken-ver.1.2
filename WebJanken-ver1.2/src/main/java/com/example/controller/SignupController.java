package com.example.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.model.SignupForm;

@Controller
public class SignupController {
	//ラジオボタン実装
	private Map<String, String> radioGender;
	//ラジオボタンの初期化メソッド
	private Map<String,String> initRadioGender(){
		Map<String, String> radio = new LinkedHashMap<>();
		//男、女をMapに格納
		radio.put("男","true");
		radio.put("女","false");
		return radio;
	}
	
	@GetMapping("/signup")
	public String getSignUp(@ModelAttribute SignupForm form, Model model) {
		//ラジオボタンの初期化メソッド呼び出し
		radioGender = initRadioGender();
		//ラジオボタン用のMapをModelに登録
		model.addAttribute("radioGender", radioGender);
		return "/html/signup";
	}
	
	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute SignupForm form, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return getSignUp(form,model);
		}
		System.out.println(form);
		return "redirect:/html/login";
	}
}
