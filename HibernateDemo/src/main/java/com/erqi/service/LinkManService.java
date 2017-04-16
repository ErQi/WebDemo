package com.erqi.service;

import com.erqi.domain.Linkman;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.16.
 * 备 注: 联系人的抽象类
 */
public interface LinkManService {
    void add(Linkman linkman)throws Exception;

    List<Linkman> find(DetachedCriteria criterion) throws Exception;

    void delete(DetachedCriteria criteria) throws Exception;

    void update(Linkman linkman) throws Exception;
}
