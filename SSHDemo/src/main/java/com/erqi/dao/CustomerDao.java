package com.erqi.dao;

import com.erqi.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 客户操作层接口
 */
public interface CustomerDao {
    int onFindCustomerCount(DetachedCriteria criteria);

    List<Customer> onFindByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
