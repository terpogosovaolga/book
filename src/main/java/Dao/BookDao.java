package Dao;

import Hibernate.HibernateSessionFactory;
import classes.Attribute;
import models.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.w3c.dom.Attr;

import java.util.ArrayList;
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
    public List<Book> getBooksAfterSearchWithParams(List<Attribute> attrs) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        String partOfQuery_genre = "(";
        String partOfQuery_price = "(";
        int i = 0, j = 0;
        for (Attribute a : attrs) {
            if (a.getName().equals("genre")) {
                if (i == 0) {
                    partOfQuery_genre += "genre = '" + a.getRightValue() + "'";
                } else {
                    partOfQuery_genre += "or genre = '" + a.getRightValue() + "'";
                }
                i++;
                System.out.println(partOfQuery_genre);
            }
            if (a.getName().equals("cout")) {
                if (j == 0) {
                    partOfQuery_price += "cout <= '" + a.getRightValue() + "'";
                } else {
                    partOfQuery_price += "or cout <= '" + a.getRightValue() + "'";
                }
                j++;
                System.out.println(partOfQuery_price);
            }
        }
        partOfQuery_genre +=")";
        partOfQuery_price +=")";
        System.out.println("GENRE: " + partOfQuery_genre);
        System.out.println("PRICE: " + partOfQuery_price);
        String fullQuery = null;
        if (partOfQuery_genre.length() > 2) {
            fullQuery = partOfQuery_genre;
            if (partOfQuery_price.length() > 2)
                fullQuery += "AND"+partOfQuery_price;
        }
        else if (partOfQuery_price.length() >2) {
            fullQuery = partOfQuery_price;
        }
        System.out.println(fullQuery);
        Query query = session.createQuery("from Book where " + fullQuery);
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
