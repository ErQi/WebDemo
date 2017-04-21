package com.erqi.dao.impl;

import com.erqi.dao.UserDao;
import com.erqi.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.17.
 * 备 注: User操作实现类
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    /**
     * 根据用户登陆信息去查找用户数据
     */
    @Override
    public User login(User user) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class).add(Restrictions.eq("account", user.getAccount())).add(Restrictions.eq("password", user.getPassword()));
        List<User> list = (List<User>) getHibernateTemplate().findByCriteria(criteria);
        return list.size() == 0 ? null : list.get(0);
    }
}
