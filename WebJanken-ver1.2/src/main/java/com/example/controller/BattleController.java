package com.example.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.model.SignupForm;
import com.example.domain.model.User;
import com.example.domain.service.UserService;
import com.example.janken.Character;
import com.example.janken.Cpu;
import com.example.janken.Judge;

@Controller
public class BattleController {
	@Autowired
	UserService userService;
	
	//性別ステータスのラジオボタン用変数
	private Map<String, String> radioGender;
	
	//ラジオボタンの初期化メソッド
	private Map<String, String> initRadioGender(){
		Map<String, String> radio = new LinkedHashMap<>();
		
		//男女をMapに格納
		radio.put("男", "true");
		radio.put("女", "false");
		
		return radio;
	}
	
	@GetMapping(value="/html/battle")
	public String GetBattle(Model model) {
		model.addAttribute("contents", "html/battle :: battle_contents");
		String initHand = "";
		model.addAttribute("playerHand", initHand);
		model.addAttribute("cpuHand", initHand);
		return "html/battleLayout";
	}
	
	//ユーザ一覧画面のGET用メソッド
	@GetMapping("/html/userList")
	public String getUserList(Model model) {
		model.addAttribute("contents", "html/userList :: userList_contents");
		//ユーザ一覧の生成
		List<User> userList = userService.selectMany();
		//Modelにユーザリストを登録
		model.addAttribute("userList", userList);
		//データ件数を取得
		int count = userService.count();
		model.addAttribute("userListCount", count);
		return "html/battleLayout";
	}
	
	//ユーザ詳細画面のGEメソッド用処理
	@GetMapping("/userDetail/{id:.+}")
	public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") String userId) {
		//ユーザーID確認（デバッグ）
		System.out.println("userId = " + userId);
		//コンテンツ部分にユーザ詳細を表示するための文字列を登録
		model.addAttribute("contents", "html/userDetail::userDetail_contents");
		//性別ステータス用ラジオボタンの初期化
		radioGender = initRadioGender();
		//ラジオボタン用のMapをModelに登録
		model.addAttribute("radioGender", radioGender);
		//ユーザーIDのチェック
		if(userId != null && userId.length() > 0) {
			//ユーザ情報を取得
			User user = userService.selectOne(userId);
			//Userクラスをフォームクラスに変換
			form.setUserId(user.getUserId());
			form.setUserName(user.getUserName());
			form.setGender(user.isGender());
			
			model.addAttribute("signupForm", form);
		}
		return "html/battleLayout";
	}
	
	//ユーザ更新用処理
	@PostMapping(value = "/userDetail", params = "update")
	public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
		System.out.println("更新ボタンの処理");
		
		//Userインスタンスの生成
		User user = new User();
		
		//フォームクラスをUserクラスに変換
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setGender(form.isGender());

		//更新実行
		boolean result = userService.updateOne(user);
		
		if(result == true) {
			model.addAttribute("result", "更新成功");
		}else {
			model.addAttribute("result", "更新失敗");
		}
		//ユーザ一覧画面を表示
		return getUserList(model);
	}
	
	@PostMapping(value = "html/userDetail", params = "delete")
	public String postUserDetailDelete(@ModelAttribute SignupForm form, Model model) {
		System.out.println("削除ボタンの処理");
		
		//Userインスタンスの生成
		User user = new User();
		
		//フォームクラスをUserクラスに変換
		
		//削除実行
		boolean result = userService.deleteOne(form.getUserId());
		
		if(result == true) {
			model.addAttribute("result", "削除成功");
		}else {
			model.addAttribute("result", "削除失敗");
		}
		
		//ユーザ一覧画面を表示
		return getUserList(model);
	}
	
	//ユーザ一覧のCSV出力用メソッド
	@GetMapping("/userList/csv")
	public String getUserListCsv(Model model) {
		return getUserList(model);
	}
	
	@PostMapping(value="/html/battle")
	public String send(@RequestParam(value="radio1", required=false)int radio1, Model model) {
		model.addAttribute("contents", "html/battle :: battle_contents");
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
		
		return "/html/battleLayout";
	}
}
