package com.erqi.service.impl;

import com.erqi.dao.LinkmanDao;
import com.erqi.dao.impl.LinkmanDaoImpl;
import com.erqi.domain.Linkman;
import com.erqi.service.LinkManService;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.16.
 * 备 注: 联系人的操作实现类
 */
public class LinkManServiceImpl implements LinkManService {
    /**
     * 将联系人添加到数据库
     */
    @Override
    public void add(Linkman linkman) throws Exception {
        LinkmanDao dao = new LinkmanDaoImpl();
        dao.add(linkman);
    }

    /**
     * 根据条件去查找返回对象集合
     */
    @Override
    public List<Linkman> find(DetachedCriteria criterion) {
        return new LinkmanDaoImpl().find(criterion);
    }
}
