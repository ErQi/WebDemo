package com.erqi.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.13.
 * 备 注: hibernate的简单的工具类
 */
public class HibernateUtil {
    private static final Configuration CONFIG;
    private static final SessionFactory FACTORY;

    static {
        CONFIG = new Configuration().configure();
        FACTORY = CONFIG.buildSessionFactory();
    }


    /**
     * SessionFactory是重量级的,那么最好将其唯一化.
     * @return 返回用到的Session对象
     */
    public static Session getSession(){
        return FACTORY.openSession();
    }
}
