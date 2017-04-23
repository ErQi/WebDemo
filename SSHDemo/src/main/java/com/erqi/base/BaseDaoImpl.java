package com.erqi.base;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.23.
 * 备 注: 操作层父类的基本实现
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    private Class clazz;

    public BaseDaoImpl() {
        // this表示的子类，c表示就是CustomerDaoImpl的Class对象
        Class c = this.getClass();
        // CustomerDaoImpl extends BaseDaoImpl<Customer>  map<k,v>
        // 第2步：获取到是BaseDaoImpl<Customer>
        Type type = c.getGenericSuperclass();

        // 目的：把type接口转换成子接口
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;

            // 获取到 Customer
            Type[] types = pType.getActualTypeArguments();
            this.clazz = (Class) types[0];
        }
    }

    /**
     * 保存指定数据
     *
     * @param t 保存的数据
     */
    @Override
    public void onSave(T t) {
        this.getHibernateTemplate().save(t);
    }

    /**
     * 跟新指定数据
     *
     * @param t 需要更新的数据
     */
    @Override
    public void onUpdate(T t) {
        this.getHibernateTemplate().save(t);
    }

    /**
     * 删除指定数据
     *
     * @param t 需要删除的数据
     */
    @Override
    public void onDelete(T t) {
        this.getHibernateTemplate().delete(t);
    }

    /**
     * 根据id查找指定数据
     *
     * @param id 指定数据的ID
     * @return 返回查找到的数据
     */
    @Override
    public T onFindById(Long id) {
        return (T) this.getHibernateTemplate().get(clazz, id);
    }

    /**
     * 根据id查找指定数据
     *
     * @param id 指定数据的ID
     * @return 返回查找到的数据
     */
    @Override
    public T onFindById(String id) {
        return (T) this.getHibernateTemplate().get(clazz, id);
    }

    /**
     * 根据条件查询所有符合条件的数据
     *
     * @param criteria 查找的条件
     * @return 符合条件的数据
     */
    @Override
    public List<T> onFindAll(DetachedCriteria criteria) {
        return (List<T>) this.getHibernateTemplate().findByCriteria(criteria);
    }

    /**
     * 根据条件进行分页查找
     *
     * @param pageCode 当前查找的页数
     * @param pageSize 每页查找数据的条数
     * @param criteria 查找的条件
     * @return 符合条件的指定页的数据
     */
    @Override
    public List<T> onFindByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
        return (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode - 1) * pageSize, pageSize);
    }
}
