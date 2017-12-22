package com.wiceflow.frame.hibernate.CRUDTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by BF on 2017/12/22.
 *
 */
public class Test {
    private static SessionFactory sessionFactory;
    public static void main(String[] args) {
        sessionFactory = new Configuration().configure("hibernate/hibernateLearn.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
//        User u1 = new User();
//        User u2 = new User();
//        u1.setName("文冰峰");
//        u2.setName("杨燕妮");
//        Group g1 = new Group();
//        g1.setName("G1");
//        g1.getUsers().add(u1);
//        g1.getUsers().add(u2);
//
//        session.save(g1);
        Group g2 = session.get(Group.class,1);
        session.getTransaction().commit();

    }
}
