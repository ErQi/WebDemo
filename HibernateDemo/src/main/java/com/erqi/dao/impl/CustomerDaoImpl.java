package com.erqi.dao.impl;

import com.erqi.dao.CustomerDao;
import com.erqi.domain.Customer;
import com.erqi.util.HibernateUtil;
import org.hibernate.Session;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.14.
 * 备 注: 用户操作的实现层
 */
public class CustomerDaoImpl implements CustomerDao {
    /**
     * 将用户添加到数据库
     * @param customer  添加的用户
     */
    @Override
    public void add(Customer customer) {
        Session session = HibernateUtil.getSession();
        session.save(customer);
        session.close();
    }
}
