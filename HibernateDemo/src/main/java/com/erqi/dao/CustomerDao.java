package com.erqi.dao;

import com.erqi.domain.Customer;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.14.
 * 备 注: 用户操作的接口层
 */
public interface CustomerDao {
    void add(Customer customer) throws Exception;

    List<Customer> queryList() throws Exception;

    Customer findUser(Long cust_id) throws Exception;

    void update(Customer customer) throws  Exception;

    void delete(Customer customer) throws  Exception;
}
