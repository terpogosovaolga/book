package Dao;

import models.User;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import Hibernate.HibernateSessionFactory;

public class UserDao implements IUserDao {


    @Autowired
    private final JdbcTemplate jdbcTemplate;

   @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>)  HibernateSessionFactory.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }

    @Override
    public User getUserById(Long id) {
       return  HibernateSessionFactory.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public void save(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from User where username = :username");
        query.setParameter("username", username);
        Transaction tx1 = session.beginTransaction();
        User user = (User) query.uniqueResult();
        tx1.commit();
        session.close();
        return user;
    }
/*
    @Override
    public User login(String email, String password) {
        try {
          User user = getUserByEmail(email);
          System.out.println("LOGIN: user id: " + user.getId());
          return user;
       }
       catch (NullPointerException e)
       {
           return null;
       }
    }*/
/*
    @Override
    public Long register(String email, String password) {
        User user = getUserByEmail(email);
        try {
            if (!user.equals(null)) {
                return Long.MIN_VALUE;
            }
        }
        catch(NullPointerException e) {
            System.out.println("we have no this email!");
            jdbcTemplate.update("insert into users(Email, Password) values(?, ?, ?, ?, ?)",
                                new Object[]{email, password});

            return getUserByEmail(email).getId();
        }
        return getUserByEmail(email).getId();
    }
*/
}