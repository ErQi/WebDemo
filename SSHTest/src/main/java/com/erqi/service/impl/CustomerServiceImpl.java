package com.erqi.service.impl;

import com.erqi.dao.CustomerDao;
import com.erqi.domain.Customer;
import com.erqi.service.CustomerService;
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
        System.err.println("注入成功  CustomerServiceImpl.java:29");
    }

    /**
     * 新增用户
     *
     * @param customer 用户
     */
    @Override
    public void add(Customer customer) throws Exception {
        customerDao.add(customer);
    }

    /**
     * 查询所有用户
     *
     * @return 返回用户集合
     */
    @Override
    public List<Customer> queryList() throws Exception {
        return customerDao.queryList();
    }

    /**
     * 查找指定用户
     *
     * @param cid 用户ID
     */
    @Override
    public Customer findUser(Long cid) throws Exception {
        return customerDao.findUser(cid);
    }

    /**
     * 更新用户信息
     *
     * @param customer 新的用户信息
     */
    @Override
    public void update(Customer customer) throws Exception {
        customerDao.update(customer);
    }

    @Override
    public void delete(Long cid) throws Exception {
        customerDao.delete(customerDao.findUser(cid));
    }

    @Override
    public List<Customer> filterFind(String filter) throws Exception {
        return customerDao.filterFindName(filter);
    }

    @Override
    public List<Customer> filterFind(DetachedCriteria criterion) throws Exception {
        return customerDao.filterFindName(criterion);
    }
}
