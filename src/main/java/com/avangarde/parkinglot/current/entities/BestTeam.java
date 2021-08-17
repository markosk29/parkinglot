package com.avangarde.parkinglot.current.entities;
/*
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BestTeam {
    private SessionFactory sessionFactory = buildSessionFactory();

    private SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {
                StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml_").build();

                Metadata metaData = new MetadataSources(standardRegistry)
                        .getMetadataBuilder()
                        .build();

                sessionFactory = metaData.getSessionFactoryBuilder().build();
            }
            return sessionFactory;
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void shutdown() {
        this.getSessionFactory().close();
    }
}
*/