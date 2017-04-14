package com.erqi.service.impl;

import com.erqi.dao.CustomerDao;
import com.erqi.dao.impl.CustomerDaoImpl;
import com.erqi.domain.Customer;
import com.erqi.service.UserService;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.14.
 * 备 注: user业务的实现类
 */
public class UserServiceImpl implements UserService {

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
    public List<Customer> queryList() {
        CustomerDao dao = new CustomerDaoImpl();
        return dao.queryList();
    }

    /**
     * 查找指定用户
     * @param cust_id 用户ID
     */
    @Override
    public Customer findUser(Long cust_id) {
        CustomerDao dao = new CustomerDaoImpl();
        return dao.findUser(cust_id);
    }

    /**
     * 更新用户信息
     * @param customer  新的用户信息
     */
    @Override
    public void update(Customer customer) throws Exception {
        CustomerDao dao = new CustomerDaoImpl();
        dao.update(customer);
    }

    @Override
    public void delete(Long cid) throws Exception {
        CustomerDao dao = new CustomerDaoImpl();
        dao.delete(dao.findUser(cid));
    }
}
