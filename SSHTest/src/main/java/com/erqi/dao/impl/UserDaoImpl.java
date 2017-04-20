package com.erqi.dao.impl;

import com.erqi.dao.UserDao;
import com.erqi.domain.User;
import com.erqi.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.17.
 * 备 注: User操作实现类
 */
public class UserDaoImpl implements UserDao {
    /**
     * 根据用户登陆信息去查找用户数据
     */
    @Override
    public User login(User user) throws Exception {
        Session session = HibernateUtils.getSession();
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class).add(Restrictions.eq("account", user.getAccount())).add(Restrictions.eq("password", user.getPassword()));
        List<User> list = criteria.getExecutableCriteria(session).list();
        return list.size() == 0 ? null : list.get(0);
    }
}
