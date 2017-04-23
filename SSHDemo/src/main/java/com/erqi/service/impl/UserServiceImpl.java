package com.erqi.service.impl;

import com.erqi.dao.UserDao;
import com.erqi.domain.User;
import com.erqi.service.UserService;
import com.erqi.util.MD5Utils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 用户业务层实现
 */
@Transactional
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 根据账户查找用户
     */
    @Override
    public User find(String user_acct) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        return userDao.onFindById(user_acct);
    }

    /**
     * 保存新用户
     */
    @Override
    public void onSave(User user) {
        user.setUser_pwd(MD5Utils.md5(user.getUser_pwd()));
        userDao.onSave(user);
    }

    /**
     * 登陆用户
     */
    @Override
    public User onLogin(User user) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("user_acct", user.getUser_acct()));
        criteria.add(Restrictions.eq("user_pwd", MD5Utils.md5(user.getUser_pwd())));
        List<User> users = userDao.onFindAll(criteria);
        return users.size() == 0 ? null : users.get(0);
    }
}
