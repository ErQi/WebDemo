package com.erqi.base;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.23.
 * 备 注: 操作层接口的基类
 */
public interface BaseDao<T> {
    void onSave(T t);

    void onUpdate(T t);

    void onDelete(T t);

    T onFindById(Long id);

    T onFindById(String id);

    List<T> onFindAll(DetachedCriteria criteria);

    List<T> onFindByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
