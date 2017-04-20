package com.erqi.service;

import com.erqi.domain.User;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.17.
 * 备 注: User的业务类
 */
public interface UserService {

    User login(User user) throws Exception;
}
