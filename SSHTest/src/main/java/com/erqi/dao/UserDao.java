package com.erqi.dao;

import com.erqi.domain.User;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.17.
 * 备 注: User的操作接口
 */
public interface UserDao {

    User login(User user) throws Exception;
}
