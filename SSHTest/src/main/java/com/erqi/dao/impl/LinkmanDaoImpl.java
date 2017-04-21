package com.erqi.dao.impl;

import com.erqi.dao.LinkmanDao;
import com.erqi.domain.Linkman;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.16.
 * 备 注: 联系人操作的实现类
 */
public class LinkmanDaoImpl extends HibernateDaoSupport implements LinkmanDao {
    /**
     * 操作添加联系人的实现
     */
    @Override
    public void add(Linkman linkman) throws Exception {
        getHibernateTemplate().save(linkman);
    }

    /**
     * 操作根据条件返回查找对象
     */
    @Override
    public List<Linkman> find(DetachedCriteria criterion) throws Exception {
        return (List<Linkman>) getHibernateTemplate().findByCriteria(criterion);
    }

    /**
     * 删除指定的联系人
     */
    @Override
    public void delete(Linkman linkman) throws Exception {
        getHibernateTemplate().delete(linkman);
    }

    @Override
    public void update(Linkman linkman) throws Exception {
       getHibernateTemplate().update(linkman);
    }
}
