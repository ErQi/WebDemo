package com.erqi.service.impl;

import com.erqi.dao.CustomerDao;
import com.erqi.dao.impl.CustomerDaoImpl;
import com.erqi.domain.Customer;
import com.erqi.service.UserService;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.14.
 * 备 注: user业务的实现类
 */
public class UserServiceImpl implements UserService {

    /**
     * 新增用户
     * @param customer 用户
     */
    @Override
    public void add(Customer customer) {
        CustomerDao dao = new CustomerDaoImpl();
        dao.add(customer);
    }
}
