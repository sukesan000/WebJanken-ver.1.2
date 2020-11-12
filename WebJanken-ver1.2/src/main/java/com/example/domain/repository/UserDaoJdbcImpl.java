package com.example.domain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.model.User;

@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao{
		@Autowired
		JdbcTemplate jdbc;
		
		//Userテーブルの件数を取得
		@Override
		public int count() throws DataAccessException{
		//全権取得してカウント
			int count = jdbc.queryForObject("SELECT COUNT(*)FROM m_user", Integer.class);
			return count;
		}
		
		@Override
		public int insertOne(User user) throws DataAccessException{
			int rowNumber = jdbc.update("INSERT INTO m_user(user_id,"
	                + " password,"
	                + " user_name,"
	                + " gender)"
	                + " VALUES(?, ?, ?, ?)",
	                user.getUserId(),
	                user.getPassword(),
	                user.getUserName(),
	                user.isGender());

	        return rowNumber;
		}
		
		//Userテーブルのデータを1件取得
		@Override
		public User selectOne(String userId) throws DataAccessException{
			//一軒取得
			Map<String, Object> map = jdbc.queryForMap("SELECT * FROM m_user" + " WHERE user_id = ?", userId);
			//結果返却用の変数
			User user = new User();
			//取得したデータを結果返却用の変数にセットしていく
			user.setUserId((String)map.get("user_id"));
			user.setPassword((String)map.get("password"));
			user.setUserName((String)map.get("user_name"));
			user.setGender((Boolean)map.get("gender"));
			
			return user;
		}
		
		//userテーブルの全データ取得
		@Override
		public List<User> selectMany() throws DataAccessException{
			//M_USERテーブルのデータを全権取得
			List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");
			//結果返却用の変数
			List<User> userList = new ArrayList<>();
			//取得したデータを結果返却用のListに格納していく
			for(Map<String, Object>map: getList) {
				//Userインスタンスの生成
				User user =new User();
				//Userインスタンスに取得したデータをセットする
				user.setUserId((String)map.get("user_id"));
				user.setPassword((String)map.get("password"));
				user.setUserName((String)map.get("user_name"));
				user.setGender((Boolean)map.get("gender"));
				userList.add(user);
			}
			return userList;
		}
		
		//１件更新
		public int updateOne(User user) throws DataAccessException {

	        //１件更新
	        int rowNumber = jdbc.update("UPDATE M_USER"
	                + " SET"
	                + " password = ?,"
	                + " user_name = ?,"
	                + " gender = ?"
	                + " WHERE user_id = ?",
	                user.getPassword(),
	                user.getUserName(),
	                user.isGender(),
	                user.getUserId());

	        //トランザクション確認のため、わざと例外をthrowする
	        //        if (rowNumber > 0) {
	        //            throw new DataAccessException("トランザクションテスト") {
	        //            };
	        //        }

	        return rowNumber;
	    }

		
		@Override
		public int deleteOne(String userId) throws DataAccessException{
			//１件削除
			int rowNumber = jdbc.update("DELETE FROM m_user WHERE user_id = ?", userId);
			return rowNumber;
		}
	
		//SQL取得結果をサーバーにCSVで保存する
//		@Override
		public void userCsvOut() throws DataAccessException{
			//M_USERテーブルのデータを全件取得するSQL
//			String sql = "SELECT * FROM m_user";
//			//ResultSetExtractorの生成
//			UserRowCallbackHandler handler = new UserRowCallbackHandler();
//			jdbc.query(sql, handler);
		}
}

