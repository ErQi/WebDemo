package com.erqi.service.impl;

import com.erqi.dao.impl.UserDaoImpl;
import com.erqi.domain.User;
import com.erqi.service.UserService;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.17.
 * 备 注: 用户登陆操作实现类
 */
public class UserServiceImpl implements UserService {
    @Override
    public User login(User user) throws Exception {
        return new UserDaoImpl().login(user);
    }
}
