package com.wiceflow.frame.hibernate;

import com.wiceflow.frame.hibernate.ManyToOne_uni.Group;
import com.wiceflow.frame.hibernate.ManyToOne_uni.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * Created by BF on 2017/12/22.
 */
public class OneToManyANDManyToOne_bi {
    private static Configuration configuration = new Configuration().configure("hibernate/hibernateLearn.cfg.xml");
    private static SessionFactory sessionFactory = configuration.buildSessionFactory();
    @Test
    public void saveTest(){
        Session s = sessionFactory.getCurrentSession();
        User u1 = new User();
        User u2 = new User();
        u1.setName("文冰峰");
        u2.setName("杨燕妮");
        Group g1 = new Group();
        u1.setGroup(g1);
        u2.setGroup(g1);
        g1.setNAME("G1");
        g1.getUsers().add(u1);
        g1.getUsers().add(u2);
        s.beginTransaction();
        s.save(g1);
        //User u = s.get(User.class,1);
        s.getTransaction().commit();
    }
}
