package com.wiceflow.frame.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
//
//            UserEntity userEntity = new UserEntity();
//            userEntity.setName("文冰峰2");
//            userEntity.setPassword("123456");
////            userEntity.setCreateTIme(new Date());
////            userEntity.setExpireTime(new Date());
//            session.save(userEntity);
//
////            session.getTransaction().commit();
//            TeacherPK tPK = new TeacherPK();
//            tPK.setId(1);
//            tPK.setName("杨燕妮");
            Teacher teacher = new Teacher();
            teacher.setAge(18);
            teacher.setGender("英语");
//            teacher.setTeacherPK(tPK);
            teacher.setName("杨燕妮");
            session.save(teacher);
            System.out.println(teacher.getId());
            System.out.println(teacher.getGender());
            teacher.setGender("语文");
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
