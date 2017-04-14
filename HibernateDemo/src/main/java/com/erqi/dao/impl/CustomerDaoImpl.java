package com.erqi.dao.impl;

import com.erqi.dao.CustomerDao;
import com.erqi.domain.Customer;
import com.erqi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.14.
 * 备 注: 用户操作的实现层
 */
public class CustomerDaoImpl implements CustomerDao {
    /**
     * 将用户添加到数据库
     *
     * @param customer 添加的用户
     */
    @Override
    public void add(Customer customer) throws Exception {
        Session session = HibernateUtil.getSession();
        try {
            session.save(customer);
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * 从数据库这个查询所有用户
     *
     * @return 返回所有用户
     */
    @Override
    public List<Customer> queryList() {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from Customer");
        List<Customer> list = query.list();
        session.close();
        return list;
    }
}
