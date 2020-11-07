package com.example.janken;

public class Judge {
	int playerHand = 0;
	int cpuHand = 0;
	String[] hand = {"グー","チョキ","パー"};
	public void startJanken(Character player, Character cpu) {
		System.out.println("ジャンケンを開始します");
		System.out.println("出したい手を入力して下さい（数字）");
		System.out.println("0.グー　1.チョキ　2.パー");
		playerHand = player.chooseHand();
		//cpuHand = cpu.showHand();
		
		System.out.print("あなたの手:" + hand[playerHand]);
		System.out.print("CPUの手:");
		printHand(cpuHand);
		
		//Character winner = judgeJanken(player, cpu);
//		if(winner != null) {
//			System.out.println("勝者は" + winner.getName() + "です");	
//		}else {
//			System.out.println("あいこです");
//		}
	}
	
	public String judgeJanken(int playerHand, int cpuHand) {
		String winWord = "";
		if((playerHand == Character.STONE && cpuHand == Character.SCISSORS)
				||(playerHand == Character.SCISSORS && cpuHand == Character.PAPER)
				||(playerHand == Character.PAPER && cpuHand == Character.STONE))
			{
				winWord = "あなたの勝ちです！";
			}else if((playerHand == Character.STONE && cpuHand == Character.PAPER)
					||(playerHand == Character.SCISSORS && cpuHand == Character.STONE)
					||(playerHand == Character.PAPER && cpuHand == Character.SCISSORS)) 
			{
				winWord = "CPUの勝ちです...";
			}else {
				winWord = "引き分けです";
			}
		return winWord;
	}
	
	public String printHand(int hand) {
		String stringHand = "";
		switch(hand) {
		case Character.STONE: 
//			System.out.println("グー");
			stringHand = "グー";
			break;
			
		case Character.SCISSORS: 
//			System.out.println("チョキ");
			stringHand = "チョキ";
			break;
			
		case Character.PAPER: 
//			System.out.println("パー");
			stringHand = "パー";
			break;
		
		default:
			break; 
		
		}
		return stringHand;
	}
}
