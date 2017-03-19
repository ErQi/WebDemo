package com.erqi.hello;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

	/**
	 * 登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户
	 * @throws SQLException 
	 */
	public User getUserByUsernameAndPwd(String username, String password) throws SQLException {
		//创建queryrunner
//		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		onTest();
		//编写sql
		System.setProperty("com.mchange.v2.c3p0.cfg.xml","classloader:c3p0-config.xml");
		String sql="select * from user where username = tom and password = 123";
		ComboPooledDataSource source = new ComboPooledDataSource();
		System.err.println(source.getUser());
		System.err.println(source.getPassword());
		Connection connection = source.getConnection();
		CallableStatement call = connection.prepareCall(sql);
		ResultSet set = call.executeQuery();
		//执行sql
//		User user = qr.query(sql, new BeanHandler<User>(User.class), username,password);
		return new User();
	}

	private void onTest() {
		ClassLoader classLoader = this.getClass().getClassLoader();
	}

}
