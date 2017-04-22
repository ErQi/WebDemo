package com.erqi.service;

import com.erqi.domain.User;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 业务层接口
 */
public interface UserService {
    User find(String user_acct);

    void onSave(User user);

    User onLogin(User user);
}
