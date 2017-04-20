package com.erqi.service.impl;

import com.erqi.dao.CustomerDao;
import com.erqi.dao.impl.CustomerDaoImpl;
import com.erqi.domain.Customer;
import com.erqi.service.CustomerService;
import com.erqi.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.14.
 * 备 注: user业务的实现类
 */
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * 新增用户
     *
     * @param customer 用户
     */
    @Override
    public void add(Customer customer) throws Exception {
        CustomerDao dao = new CustomerDaoImpl();
        dao.add(customer);
    }

    /**
     * 查询所有用户
     *
     * @return 返回用户集合
     */
    @Override
    public List<Customer> queryList() throws Exception {
        CustomerDao dao = new CustomerDaoImpl();
        return dao.queryList();
    }

    /**
     * 查找指定用户
     *
     * @param cid 用户ID
     */
    @Override
    public Customer findUser(Long cid) throws Exception {
        CustomerDao dao = new CustomerDaoImpl();
        return dao.findUser(cid);
    }

    /**
     * 更新用户信息
     *
     * @param customer 新的用户信息
     */
    @Override
    public void update(Customer customer) throws Exception {
        CustomerDao dao = new CustomerDaoImpl();
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            dao.update(customer);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Long cid) throws Exception {
        CustomerDao dao = new CustomerDaoImpl();
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            dao.delete(dao.findUser(cid));
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            throw e;
        }
    }

    @Override
    public List<Customer> filterFind(String filter) throws Exception {
        CustomerDao dao = new CustomerDaoImpl();
        return dao.filterFindName(filter);
    }

    @Override
    public List<Customer> filterFind(DetachedCriteria criterion) throws Exception {
        return new CustomerDaoImpl().filterFindName(criterion);
    }
}
