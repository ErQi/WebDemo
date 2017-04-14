package com.erqi.service;

import com.erqi.domain.Customer;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.14.
 * 备 注: user业务的抽象类
 */
public interface UserService {
    void add(Customer customer) throws  Exception;

    List<Customer> queryList() throws Exception;

    Customer findUser(Long cid) throws Exception;

    void update(Customer customer) throws Exception;

    void delete(Long cid) throws Exception;
}
