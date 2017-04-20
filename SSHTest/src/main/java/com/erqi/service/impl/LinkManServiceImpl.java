package com.erqi.service.impl;

import com.erqi.dao.LinkmanDao;
import com.erqi.dao.impl.LinkmanDaoImpl;
import com.erqi.domain.Linkman;
import com.erqi.service.LinkManService;
import com.erqi.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.16.
 * 备 注: 联系人的操作实现类
 */
public class LinkManServiceImpl implements LinkManService {
    private LinkmanDao linkmanDao;

    public void setLinkmanDao(LinkmanDao linkmanDao) {
        this.linkmanDao = linkmanDao;
    }

    /**
     * 将联系人添加到数据库
     */
    @Override
    public void add(Linkman linkman) throws Exception {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            linkmanDao.add(linkman);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            throw e;
        }
    }

    /**
     * 根据条件去查找返回对象集合
     */
    @Override
    public List<Linkman> find(DetachedCriteria criterion) throws Exception {
        return new LinkmanDaoImpl().find(criterion);
    }

    /**
     * 删除指定条件用户
     */
    @Override
    public void delete(DetachedCriteria criteria) throws Exception {
        Linkman linkman = find(criteria).get(0);
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            linkmanDao.delete(linkman);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            throw e;
        }
    }

    /**
     * 更新指定用户信息
     */
    @Override
    public void update(Linkman linkman) throws Exception {
        Session session = HibernateUtils.getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            linkmanDao.update(linkman);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            throw e;
        }
    }
}
