package com.wiceflow.frame.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * Created by BF on 2017/12/21.
 */

public class TeacherTest {
    public static Configuration cfg = new Configuration().configure();
    public static SessionFactory sessionFactory = cfg.buildSessionFactory();
    @Test
    public void loadDB(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Teacher t = session.load(Teacher.class,2);
        //System.out.println(t); 当我load后没有执行对象的使用直接commit关闭了session
        session.getTransaction().commit();
        //System.out.println(t.getName());
        // 这时候这里就会报错，因为它load的时候是存在缓存了，
        // 当session关闭了，这个对象就没了，会报no session异常
        System.out.println(t);
    }
}
