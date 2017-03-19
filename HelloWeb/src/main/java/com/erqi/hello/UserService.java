package com.erqi.hello;

import java.sql.SQLException;

public class UserService {

	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return User 用户
	 * @throws SQLException 
	 */
	public User login(String username, String password) throws SQLException {
		//调用dao
		UserDao dao=new UserDao();
		
		return dao.getUserByUsernameAndPwd(username,password);
	}

}
