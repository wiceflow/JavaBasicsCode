package com.wiceflow.frame.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;

import java.util.EnumSet;

/**
 * Created by BF on 2017/12/21.
 * 测试一对一关系
 */

public class HibernateTestOneToOneTest {
    private static Configuration configuration = new Configuration().configure("hibernate/hibernateLearn.cfg.xml");
    private static SessionFactory sessionFactory = configuration.buildSessionFactory();

    @Test
    public void testOneToOne(){
        Session session = sessionFactory.getCurrentSession();
    }

    @Test
    public void testSchemaExport(){
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate/hibernateLearn.cfg.xml").build();
        Metadata metadata = new MetadataSources(serviceRegistry).buildMetadata();
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.create(EnumSet.of(TargetType.DATABASE), metadata);
    }

}
