package Hibernate;

import models.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactory() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure(new File("C:\\Users\\olyao\\IdeaProjects\\book\\src\\hibernate.cfg.xml"));
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Role.class);
                configuration.addAnnotatedClass(Book.class);
                configuration.addAnnotatedClass(Basket.class);
                configuration.addAnnotatedClass(BasketParagraph.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}