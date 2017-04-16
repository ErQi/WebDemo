package com.erqi.dao.impl;

import com.erqi.dao.LinkmanDao;
import com.erqi.domain.Linkman;
import com.erqi.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.16.
 * 备 注: 联系人操作的实现类
 */
public class LinkmanDaoImpl implements LinkmanDao {
    /**
     * 操作添加联系人的实现
     */
    @Override
    public void add(Linkman linkman) throws Exception {
        Session session = HibernateUtils.getCurrentSession();
        session.save(linkman);
    }

    /**
     * 操作根据条件返回查找对象
     */
    @Override
    public List<Linkman> find(DetachedCriteria criterion) throws Exception {
        Session session = HibernateUtils.getSession();
        List list = criterion.getExecutableCriteria(session).list();
        session.close();
        return list;
    }

    /**
     * 删除指定的联系人
     */
    @Override
    public void delete(Linkman linkman) throws Exception {
        Session session = HibernateUtils.getCurrentSession();
        session.delete(linkman);
    }

    @Override
    public void update(Linkman linkman) throws Exception {
        Session session = HibernateUtils.getCurrentSession();
        session.update(linkman);
    }
}
