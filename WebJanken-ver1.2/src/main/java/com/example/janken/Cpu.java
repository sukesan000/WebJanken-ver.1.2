package com.example.janken;

public class Cpu extends Character{
	public String name = "CPU";
	
	public int showHand() {
		int hand = 0;
		double randomNum = Math.random() * 3;	
		if(randomNum < 1) {
			hand = 0;
		}else if(randomNum < 2) {
			hand = 1;
		}else if(randomNum < 3) {
			hand = 2;
		}
		
		return hand;
	}
	
	public String getName() {
		return name;
	}
}