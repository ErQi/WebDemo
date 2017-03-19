package com.erqi.hello;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户
     * @throws SQLException
     */
    public User getUserByUsernameAndPwd(String username, String password) throws SQLException {
        //创建queryrunner
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        //编写sql
        String sql = "select * from user where username = ? and password = ?";
        //执行sql
        User user = qr.query(sql, new BeanHandler<User>(User.class), username, password);
        return new User();
    }
}
