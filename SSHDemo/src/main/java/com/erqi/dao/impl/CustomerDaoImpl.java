package com.erqi.dao.impl;

import com.erqi.dao.CustomerDao;
import com.erqi.domain.Customer;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 客户操作层实现
 */
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {
    /**
     * 带条件的查询顾客表记录条数
     *
     * @param criteria 查询条件
     * @return 符合查询条件的记录总数
     */
    @Override
    public int onFindCustomerCount(DetachedCriteria criteria) {
        List<Number> list = (List<Number>) getHibernateTemplate().findByCriteria(criteria);
        return list.size() == 0 ? 0 : list.get(0).intValue();
    }

    /**
     * 按条件分页查询
     *
     * @param pageCode 当前页数
     * @param pageSize 每页条数
     * @param criteria 查询条件
     * @return 指定页数的数据
     */
    @Override
    public List<Customer> onFindByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        return (List<Customer>) getHibernateTemplate().findByCriteria(criteria, (pageCode - 1) * pageSize, pageSize);
    }
}
