package com.erqi.dao;

import com.erqi.domain.Linkman;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.16.
 * 备 注: 联系人的操作对象的抽象接口
 */
public interface LinkmanDao {
    void add(Linkman linkman);

    List<Linkman> find(DetachedCriteria criterion);
}
