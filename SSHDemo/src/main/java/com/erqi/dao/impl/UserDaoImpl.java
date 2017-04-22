package com.erqi.dao.impl;

import com.erqi.dao.UserDao;
import com.erqi.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 用户操作层层实现
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    /**
     * 根据条件查询用户
     */
    @Override
    public List<User> find(DetachedCriteria criteria) {
        return (List<User>) getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public void onSave(User user) {
        getHibernateTemplate().save(user);
    }
}
