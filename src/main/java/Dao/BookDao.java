package Dao;

import Hibernate.HibernateSessionFactory;
import models.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
    /*
        @Override
        public List<Book> getPopularBooks() {

            return jdbcTemplate.query("select * from Books order by Number_of_watchings desc limit 10", mapper);
        }

        @Override
        public List<Book> getNewArrivals() {

            return jdbcTemplate.query("select *  from Books order by book_id desc limit 10", mapper);
        }

        @Override
        public List<Book> getPoems() {
            return jdbcTemplate.query("select * from Books where genre = 'поэзия' limit 10", mapper);
        }

        @Override
        public List<Book> getNovels() {
            return jdbcTemplate.query("select * from books where genre = 'роман' limit 10", mapper);
        }

        @Override
        public List<Book> getBooksOfAuthor(String surname) {
            return jdbcTemplate.query("select * from books where author_surname=? limit 10", new Object[]{surname}, mapper);

        }

        @Override
        public List<Book> getBooksOfAuthor(String name, String surname){
            return jdbcTemplate.query("select * from books where author_name=? and author_surname = ? limit 10" , new Object[]{name, surname}, mapper);

        }

        @Override
        public List<Book> getSmallBooks() {
            return jdbcTemplate.query("select * from books order by pages_number limit 10", mapper);
        }
    */
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
        Query query = session.createQuery("from Book where genre = 'стихи'");
        Transaction tx1 = session.beginTransaction();
        List<Book> result = query.list();
        tx1.commit();
        session.close();
        return result;
    }

    @Override
    public List<Book> getNovels() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from Book where genre = 'роман' OR genre='роман-эпопея'");
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
/*
    @Override
    public void increaseNumberOfWatchings(Long book_id) {
        int views = jdbcTemplate.queryForObject("select Number_of_watchings  from Books where Book_id=?", new Object[]{book_id}, Integer.class);
        jdbcTemplate.update("update Books set Number_of_watchings=? where book_id=?", new Object[]{views+1, book_id});
    }*/

/*
    @Override
    public List<Book> search(String key, String value) {
        String sql = new String();


        if (key=="price")
        {
            sql = "select * from Books where Price <= " + value;
            //return jdbcTemplate.query("select * from Books where Price <= ?", new Object[]{value}, mapper);
        }
        else if (key == "language" && value == "untranslated"){
            sql = "select * from Books where language=original_language";
            //return jdbcTemplate.query("select * from Books where language=original_language", mapper);
        }
        else {
            sql = "select * from Books where " + key  +  " = '" + value + "'";
        }
        System.out.println(sql);
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public List<Book> search(Map<String, String> map) {
        String sql = "select * from books where ";
        int num = 0;
        for (Map.Entry<String, String> m : map.entrySet())
        {
            if (num>0)
                sql+="and ";
            num++;
            sql+=m.getKey() + " = '" + m.getValue() + "'";
        }
        System.out.println("query: " + sql);
        return jdbcTemplate.query(sql, mapper);
    }
*/
}
