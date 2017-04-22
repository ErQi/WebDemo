package com.erqi.dao;

import com.erqi.domain.User;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 用户操作层层接口
 */
public interface UserDao {
    List<User> find(DetachedCriteria criteria);

    void onSave(User user);
}
