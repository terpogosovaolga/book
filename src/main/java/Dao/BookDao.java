package Dao;

import Hibernate.HibernateSessionFactory;
import models.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class BookDao implements IBookDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private BookDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

        @Override
        public List<Book> getAllBooks() {
            List<Book> books = (List<Book>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From Book").list();
            return books;
        }
    @Override
    public Book getBookById(Long id) {
        return  HibernateSessionFactory.getSessionFactory().openSession().get(Book.class, id);

    }

    @Override
    public void save(Book book) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(book);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Book book) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(book);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Book> getPopularBooks() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from Book where numberOfWatching >= 1000");
        Transaction tx1 = session.beginTransaction();
        List<Book> result = query.list();
        tx1.commit();
        session.close();
        return result;
    }

    @Override
    public List<Book> getNewArrivals() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from Book where yearOfPublishing >= 2018");
        Transaction tx1 = session.beginTransaction();
        List<Book> result = query.list();
        tx1.commit();
        session.close();
        return result;
    }

    @Override
    public List<Book> getPoems() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from Book where genre = 'поэзия'");
        Transaction tx1 = session.beginTransaction();
        List<Book> result = query.list();
        tx1.commit();
        session.close();
        return result;
    }

    @Override
    public List<Book> getNovels() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from Book where genre = 'роман'");
        Transaction tx1 = session.beginTransaction();
        List<Book> result = query.list();
        tx1.commit();
        session.close();
        return result;
    }

    @Override
    public List<Book> getSmallBooks() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from Book where countOfPages < 200");
        Transaction tx1 = session.beginTransaction();
        List<Book> result = query.list();
        tx1.commit();
        session.close();
        return result;
    }

    @Override
    public List<Book> getBooksOfAuthor(String surname, String name) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from Book where authorSureName = :surname and authorName = :name");
        query.setParameter("surname", surname);
        query.setParameter("name", name);
        Transaction tx1 = session.beginTransaction();
        List<Book> result = query.list();
        tx1.commit();
        session.close();
        return result;
    }

    @Override
    public void update(Book book) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(book);
        tx1.commit();
        session.close();
    }

    @Override
    public void view(Book book) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query miniQuery = session.createQuery("select numberOfWatching from Book where id = :bookId");
        miniQuery.setParameter("bookId", book.getId());
        int views = miniQuery.getFirstResult();
        Query query = session.createQuery("update Book set numberOfWatching = :newViews where id = :bookId");
        query.setParameter("newViews", views+1);
        query.setParameter("bookId", book.getId());
        query.executeUpdate();
        tx1.commit();
        session.close();
    }
}
