package com.erqi.service.impl;

import com.erqi.dao.UserDao;
import com.erqi.dao.impl.UserDaoImpl;
import com.erqi.domain.User;
import com.erqi.service.UserService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.17.
 * 备 注: 用户登陆操作实现类
 */
@Transactional
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(User user) throws Exception {
        return new UserDaoImpl().login(user);
    }
}
