package com.erqi.dao;

import com.erqi.base.BaseDao;
import com.erqi.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 客户操作层接口
 */
public interface CustomerDao extends BaseDao<Customer> {
    int onFindCustomerCount(DetachedCriteria criteria);
}
