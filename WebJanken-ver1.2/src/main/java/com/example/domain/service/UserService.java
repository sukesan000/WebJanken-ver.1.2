package com.example.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.model.User;
import com.example.domain.repository.UserDao;

@Transactional
@Service
public class UserService {
	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;
	
	//insert用メソッド
	public boolean insert(User user) {
		int rowNumber = dao.insertOne(user);
		boolean result = false;
		if(rowNumber > 0) {
			result = true;
		}
		return result;
	}
	
	//カウント用メソッド
	public int count() {
		return dao.count();
	}
	
	//全件取得用メソッド
	public List<User> selectMany(){
		//全件取得
		return dao.selectMany();
	}
	
	//1件取得用メソッド
	public User selectOne(String userId) {
		return dao.selectOne(userId);
	}
	
	//１件更新メソッド
	public boolean updateOne(User user) {
		int rowNumber = dao.updateOne(user);
		boolean result = false;
		if(rowNumber > 0) {
			result =true;
		}		
		return result;
	}
	
	//１件削除メソッド
	public boolean deleteOne(String userId) {
		int rowNumber = dao.deleteOne(userId);
		boolean result = false;
		if(rowNumber > 0) {
			result =true;
		}		
		return result;
	}
	
	//ユーザ一覧をCSV出力する
//	public void userCsvOut() throws DataAccessException{
//		//CSV出力
//		dao.userCsvOut();
//	}
	
	//サーバーに保存されているファイルを取得して、byte配列に変換する
//	public byte[] getFile(String fileName) throws IOException{
//		//ファイルシステムの取得
//		FileSystem fs = FileSystems.getDefault();
//		//ファイル取得
//		Path p = fs.getPath(fileName);
//		//ファイルをbyte配列に変換
//		byte[] bytes = Files.readAllBytes(p);
//		return bytes;
//	}
}

