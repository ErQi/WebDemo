package com.erqi.test;

import com.erqi.domain.Customer;
import com.erqi.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.13.
 * 备 注: 测试hibernate框架
 */
public class Demo1 {

    /**
     * 测试保存客服
     */
    @Test
    public void testSave() {
        /**
         * 测试步骤
         * 1.加载配置文件
         * 2.创建SessionFactory生成Session对象
         * 3.创建Session 对象
         * 4.开启事物
         * 5.编写保存代码
         * 6.提交事务
         * 7.释放资源
         */
        // 加载配置文件,默认加载src目录下文件
        Configuration config = new Configuration().configure();
        // 创建SessionFactory对象
        SessionFactory factory = config.buildSessionFactory();
        // 创建Session对象
        Session session = factory.openSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();
        // 编写保存代码
        Customer customer = new Customer();
        customer.setCust_name("ErQi");
        customer.setCust_phone("119");
        customer.setCust_level("2");

        session.save(customer);
        // 提交事务
        transaction.commit();
        // 释放资源
        session.close();
        factory.close();
    }


    /**
     * 测试工具类的使用
     */
    @Test
    public void testSave2() {
        Session session = HibernateUtils.getSession();

        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setCust_name("ErLe");
        customer.setCust_phone("114");
        customer.setCust_level("5");

        session.save(customer);

        transaction.commit();
        session.close();
    }

    /**
     * 测试更新接口是否有效
     */
    @Test
    public void update() {
        // 原来：加载配置文件，获取Factory对象，获取session
        Session session = HibernateUtils.getSession();
        Transaction tr = session.beginTransaction();
        // 测试查询的方法 2个参数：arg0查询JavaBean的class对象 arg1主键的值
        Customer c = session.get(Customer.class, 101L);

        // 设置客户的信息
        c.setCust_name("小苍");
        c.setCust_level("3");

        // 修改
        session.update(c);

        // 提交事务
        tr.commit();
        // 释放资源
        session.close();
    }
}
