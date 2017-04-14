package com.erqi.dao.impl;

import com.erqi.dao.CustomerDao;
import com.erqi.domain.Customer;
import com.erqi.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
        session.save(customer);
        session.close();
    }

    /**
     * 从数据库这个查询所有用户
     *
     * @return 返回所有用户
     */
    @Override
    public List<Customer> queryList() throws Exception {
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
    public Customer findUser(Long cust_id) throws Exception {
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
        Session session = HibernateUtils.getCurrentSession();
        session.update(customer);
    }

    /**
     * 删除指定用户
     *
     * @param customer 指定用户的ID
     */
    @Override
    public void delete(Customer customer) throws Exception {
        Session session = HibernateUtils.getCurrentSession();
        session.delete(customer);
    }

    /**
     * 根据用户名来查找用户
     *
     * @param filter
     * @return 查找到的用户名
     */
    @Override
    public List<Customer> filterFindName(String filter) throws Exception {
        Session session = HibernateUtils.getSession();
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        filter = filter.trim();
        if (filter != null && !filter.isEmpty()) {
            criteria = criteria.add(Restrictions.like("cust_name", "%" + filter + "%"));
        }
        return criteria.getExecutableCriteria(session).list();
    }
}
