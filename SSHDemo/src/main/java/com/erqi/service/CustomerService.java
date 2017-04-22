package com.erqi.service;

import com.erqi.domain.Customer;
import com.erqi.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 客户业务层接口
 */
public interface CustomerService {
    PageBean<Customer> onFindByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
