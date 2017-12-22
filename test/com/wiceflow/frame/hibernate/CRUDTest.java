package com.wiceflow.frame.hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by BF on 2017/12/22.
 *
 */
public class CRUDTest {

    private static SessionFactory sessionFactory;
    @Before
    private static void beFore(){
        sessionFactory = new Configuration().configure("hibernate/hibernateLearn.cfg.xml").buildSessionFactory();
    }
    @Test
    public void saveTest(){

    }
}
