package com.erqi.dao.impl;

import com.erqi.dao.CustomerDao;
import com.erqi.domain.Customer;
import com.erqi.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
        Session session = HibernateUtils.getSession();
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
        Session session = HibernateUtils.getSession();
        Query query = session.createQuery("from Customer");
        List<Customer> list = query.list();
        session.close();
        return list;
    }

    /**
     * 根据用户ID查找用户
     *
     * @param cust_id 用户ID
     */
    @Override
    public Customer findUser(Long cust_id) {
        Session session = HibernateUtils.getSession();
        Customer customer = session.get(Customer.class, cust_id);
        session.close();
        return customer;
    }

    /**
     * 更新用户信息
     *
     * @param customer 新的用户信息
     */
    @Override
    public void update(Customer customer) throws Exception {
        Session session = HibernateUtils.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.update(customer);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }

    /**
     * 删除指定用户
     *
     * @param customer 指定用户的ID
     */
    @Override
    public void delete(Customer customer) throws Exception {
        Session session = HibernateUtils.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.delete(customer);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
        } finally {
            session.close();
        }
    }
}
