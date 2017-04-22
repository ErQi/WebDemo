package com.erqi.service.impl;

import com.erqi.dao.CustomerDao;
import com.erqi.domain.Customer;
import com.erqi.domain.PageBean;
import com.erqi.service.CustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 客户业务层实现
 */
public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * 分页查询顾客
     *
     * @param pageCode 页数
     * @param pageSize 每页条数
     * @param criteria 查询条件
     * @return 携带了查询结果的分页对象
     */
    @Override
    public PageBean<Customer> onFindByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        PageBean<Customer> bean = new PageBean<>();
        bean.setPageCode(pageCode);
        bean.setPageSize(pageSize);

        // 查询总记录条数
        criteria.setProjection(Projections.rowCount());
        bean.setTotalCount(customerDao.onFindCustomerCount(criteria));
        criteria.setProjection(null);

        List<Customer> list = customerDao.onFindByPage(pageCode, pageSize, criteria);
        bean.setList(list);
        return bean;
    }
}
