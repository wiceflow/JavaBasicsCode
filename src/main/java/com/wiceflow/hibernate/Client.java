package com.wiceflow.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

/**
 * Created by duxin on 2017/12/7.
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            UserEntity userEntity = new UserEntity();
            userEntity.setId("2");
            userEntity.setName("文冰峰");
            userEntity.setPassword("123456");
//            userEntity.setCreateTIme(new Date());
//            userEntity.setExpireTime(new Date());
            session.save(userEntity);

            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();

        }finally {
            if (session!=null){
                session.close();
            }
        }
    }
}
