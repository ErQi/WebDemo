package com.erqi.test;

import com.erqi.domain.Customer;
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
    public void testSave(){
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

        /**
         * 菜鸟的问题
         * 1.mysql时区问题,在
         */
    }
}
