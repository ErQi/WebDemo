package com.erqi.dao.impl;

import com.erqi.dao.CustomerDao;
import com.erqi.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.14.
 * 备 注: 用户操作的实现层
 */
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
    /**
     * 将用户添加到数据库
     *
     * @param customer 添加的用户
     */
    @Override
    public void add(Customer customer) throws Exception {
        getHibernateTemplate().save(customer);
    }

    /**
     * 从数据库这个查询所有用户
     *
     * @return 返回所有用户
     */
    @Override
    public List<Customer> queryList() throws Exception {
        return (List<Customer>) getHibernateTemplate().find("from Customer");
    }

    /**
     * 根据用户ID查找用户
     *
     * @param cust_id 用户ID
     */
    @Override
    public Customer findUser(Long cust_id) throws Exception {
        return getHibernateTemplate().get(Customer.class, cust_id);
    }

    /**
     * 更新用户信息
     *
     * @param customer 新的用户信息
     */
    @Override
    public void update(Customer customer) throws Exception {
        getHibernateTemplate().update(customer);
    }

    /**
     * 删除指定用户
     *
     * @param customer 指定用户的ID
     */
    @Override
    public void delete(Customer customer) throws Exception {
        getHibernateTemplate().delete(customer);
    }

    /**
     * 根据用户名来查找用户
     *
     * @param filter
     * @return 查找到的用户名
     */
    @Override
    public List<Customer> filterFindName(String filter) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        if (filter != null && !filter.trim().isEmpty()) {
            criteria = criteria.add(Restrictions.like("cust_name", "%" + filter.trim() + "%"));
        }
        return (List<Customer>) getHibernateTemplate().findByCriteria(criteria);
    }

    /**
     * 根据给定的条件进行集合查询
     *
     * @param Criterion 具体的条件
     */
    @Override
    public List<Customer> filterFindName(DetachedCriteria Criterion) throws Exception {
        return (List<Customer>) getHibernateTemplate().findByCriteria(Criterion);
    }
}
