package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.janken.Character;
import com.example.janken.Cpu;
import com.example.janken.Judge;

@Controller
public class BattleController {
	@GetMapping(value="/html/battle")
	public String GetBattle() {
		return "html/battle";
	}
	
	@PostMapping(value="/html/battle")
	public String send(@RequestParam(value="radio1", required=false)int radio1, Model model) {
		Judge judge = new Judge();
		Character cpu = new Cpu();
		int playerHand = 0;
		int cpuHand = 0;
		String winWord = "";
		String playerStringHand;
		String cpuStringHand;
		cpuHand = cpu.showHand();
		playerHand = radio1; 
		playerStringHand = judge.printHand(playerHand);
		cpuStringHand = judge.printHand(cpuHand);
		winWord = judge.judgeJanken(playerHand, cpuHand);
		//		if(radio1 == null) {
//			playerHand = "あなたの手: 入力されていません";
//		}
		model.addAttribute("playerHand", playerStringHand);
		model.addAttribute("cpuHand", cpuStringHand);
		model.addAttribute("winWord", winWord);
		
		return "/html/battle";
	}
}
