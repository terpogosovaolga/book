package Dao;

import Hibernate.HibernateSessionFactory;
import models.Basket;
import models.BasketParagraph;
import models.Book;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BasketParagraphDao implements IBasketParagraphDao{

    private final JdbcTemplate jdbcTemplate;

    public BasketParagraphDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

  /*  @Override
    public BasketParagraph createBasketParagraph(Long bookId, Long basketId, int number, Double cost) {
        int preNumber;
        try {
            preNumber = jdbcTemplate.queryForObject("select Count from BasketParagraphs where Book_id = ? and Basket_id=?", new Object[]{bookId, basketId}, Integer.class);
            if (preNumber != 0) {
                preNumber+=number;
                cost = (cost/number)*preNumber;
                jdbcTemplate.update("update BasketParagraphs set Count=?, Cost=? where Basket_id=? and Book_id=? ) values(?, ?, ?, ?)", new Object[]{preNumber, cost, basketId, bookId});
            }
        }
        catch(EmptyResultDataAccessException e) {
            System.out.println("creating BP.........");
            jdbcTemplate.update("insert into BasketParagraphs(Basket_id, Book_id, Count, Cost) values(?, ?, ?, ?)", new Object[]{basketId, bookId, number, cost});
        }
        return jdbcTemplate.queryForObject("select * from BasketParagraphs where Basket_id=? and Book_id=?", new Object[]{basketId, bookId}, mapper);
    }

    @Override
    public List<BasketParagraphBooked> getAllBasketParagraphsOfBasket(Long basketId) {
       List<BasketParagraphBooked> bpBooked = new ArrayList<>();
       List<BasketParagraph> bp = jdbcTemplate.query("select * from BasketParagraphs where Basket_id=?", new Object[]{basketId}, mapper);
       try {
           for (BasketParagraph b : bp) {
               Book book = jdbcTemplate.queryForObject("select * from Books where Book_id=?", new Object[]{b.getBookId()}, bookMapper);
               bpBooked.add(new BasketParagraphBooked(b, book));
           }
       }
       catch(EmptyResultDataAccessException e) {
           return bpBooked;
       }
       return bpBooked;
    }

    @Override
    public Double getSumOfBasket(Long id) {
        return jdbcTemplate.queryForObject("select sum(Cost) from basketparagraphs where Basket_id=?", new Object[]{id}, Double.class);
    }

    @Override
    public void deleteBasketParagraphs(Long basketId) {
        jdbcTemplate.update("delete from BasketParagraphs where Basket_id = ?", new Object[]{basketId});
    }

    @Override
    public BasketParagraph getBasketParagraphByBasketAndBook(Long basketId, Long bookId) {
        try {
            BasketParagraph bp = jdbcTemplate.queryForObject("select * from BasketParagraphs where basket_id=? and book_id = ?", new Object[]{basketId, bookId}, mapper);
            if (!bp.equals(null))
            {
                return bp;
            }
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
        return null;
    }

    @Override
    public void plusNumberOfBooks(Long bpId) {
        int preNumber = jdbcTemplate.queryForObject("select NumberOfBooks from BasketParagraphs where BasketParagraph_id=?", new Object[]{bpId}, Integer.class);
        long bookId = jdbcTemplate.queryForObject("select Book_id from BasketParagraphs where BasketParagraph_id = ?", new Object[]{bpId}, Long.class);
        Double price = jdbcTemplate.queryForObject("select Price from Books where Book_id = ?", new Object[]{bookId}, Double.class);

        jdbcTemplate.update("update BasketParagraphs set Count=?, Cost=? where BasketParagraph_id=?", new Object[]{preNumber++, price*preNumber, bpId});

    }

    @Override
    public void minusNumberOfBooks(Long bpId) {
        int preNumber=jdbcTemplate.queryForObject("select NumberOfBooks from BasketParagraphs where BasketParagraph_id=?", new Object[]{bpId}, Integer.class);
        if (preNumber==1)
        {
           deleteBasketParagraph(bpId);
        }
        else {
            long bookId = jdbcTemplate.queryForObject("select Book_id from BasketParagraphs where BasketParagraph_id = ?", new Object[]{bpId}, Long.class);
            Double price = jdbcTemplate.queryForObject("select Price from Books where Book_id = ?", new Object[]{bookId}, Double.class);
            jdbcTemplate.update("update BasketParagraphs set Count=?, Cost=? where BasketParagraph_id=?", new Object[]{preNumber--, price*preNumber, bpId});
        }
    }

    @Override
    public List<BasketParagraphBooked> getAllBasketParagraphsOfOrder(Long id) {
        List<Basket> orders = jdbcTemplate.query("select * from Baskets where user_id=? and Date_of_purchase is not null", new Object[]{id}, basketMapper);
        List<BasketParagraphBooked> bps = new ArrayList<>();
        for (Basket  o : orders)
        {
           List<BasketParagraphBooked> list =  getAllBasketParagraphsOfBasket(o.getId());
           for (BasketParagraphBooked b : list)
               bps.add(b);
        }
        return bps;
    }

    @Override
    public List<BasketParagraph> getAllBasketParagraphsWithBook(Long book_id) {
        return jdbcTemplate.query("select * from BasketParagraphs where Book_id=?", new Object[]{book_id}, mapper);
    }

    @Override
    public void setPrice(Long id, Double newPrice) {
        //int count = jdbcTemplate.queryForObject("select Count from BasketParagraphs where BasketParagraph_id=?", new Object[]{id}, Integer.class);

        jdbcTemplate.update("update BasketParagraphs set Cost=? where BasketParagraph_id=?", new Object[]{newPrice, id});
    }*/

    @Override
    public List<BasketParagraph> getAllBasketParagraphs() {
        List<BasketParagraph> bps = (List<BasketParagraph>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From BasketParagraphs").list();
        return bps;
    }

    @Override
    public void delete(BasketParagraph basketParagraph) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(basketParagraph);
        tx1.commit();
        Transaction tx2 = session.beginTransaction();
        Query query = session.createQuery("update Basket set sum = sum - :oldSum where id = :basketId");
        query.setParameter("oldSum", basketParagraph.getSum());
        query.setParameter("basketId", basketParagraph.getBasketId());
        tx2.commit();
        session.close();
    }

    @Override
    public List<BasketParagraph> getBasketParagraphsOfBook(Book book) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("from BasketParagraph where bookId = :bookId");
        query.setParameter("bookId", book.getId());
        List<BasketParagraph> result = query.list();
        tx1.commit();
        session.close();
        return result;
    }

    @Override
    public List<BasketParagraph> getBasketParagraphsOfBasket(Basket basket) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("from BasketParagraph where basketId = :basketId");
        query.setParameter("basketId", basket.getId());
        List<BasketParagraph> result = query.list();
        tx1.commit();
        session.close();
        return result;
    }

    @Override
    public BasketParagraph getBasketParagraphByBasketAndBook(long basketId, long bookId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("from BasketParagraph where basketId = :basketId and bookId= :bookId");
        query.setParameter("basketId", basketId);
        query.setParameter("bookId", bookId);
        BasketParagraph bp = (BasketParagraph) query.uniqueResult();
        tx1.commit();
        session.close();
        return bp;
    }

    @Override
    public void updateSum(Double differenceBetweenOldAndNewPrice, BasketParagraph bp) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("update BasketParagraph set sum =: newSum where id= :basketParagraphId");
        query.setParameter("newSum", bp.getSum() + differenceBetweenOldAndNewPrice * bp.getNumberOfBooks());
        query.setParameter("basketParagraphId", bp.getId());
        query.executeUpdate();
        tx1.commit();
        Transaction tx2 = session.beginTransaction();
        Query queryForBasket = session.createQuery("update Basket set sum = sum + :newSum  where id = :basketId");
        queryForBasket.setParameter("basketId", bp.getBasketId());
        queryForBasket.setParameter("newSum",  differenceBetweenOldAndNewPrice * bp.getNumberOfBooks());
        tx2.commit();
        session.close();
    }

    @Override
    public void updateNumberOfBooks(int plusNumber, BasketParagraph bp) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query priceQuery = session.createQuery("from Book where id = :bookId");
        priceQuery.setParameter("bookId", bp.getBookId());
        Book book = (Book) priceQuery.uniqueResult();
        Query query = session.createQuery("update BasketParagraph set numberOfBooks = numberOfBooks + :plusNumber, sum = sum + :newSum where id = :bpId");
        query.setParameter("plusNumber", plusNumber);
        query.setParameter("newSum", plusNumber*book.getCout());
        query.setParameter("bpId", bp.getId());
        query.executeUpdate();
        tx.commit();

        session.close();
    }

    @Override
    public BasketParagraph save(BasketParagraph basketParagraph) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query checkQuery = session.createQuery("from BasketParagraph where basketId= :basketId and bookId= :bookId");
        checkQuery.setParameter("basketId", basketParagraph.getBasketId());
        checkQuery.setParameter("bookId", basketParagraph.getBookId());
        if (checkQuery.uniqueResult() == null)
        {
            session.save(basketParagraph);
            tx1.commit();
            session.close();
            return null;
        }
        else
        {
            updateNumberOfBooks(1, basketParagraph);
            return basketParagraph;
        }
    }

    @Override
    public BasketParagraph getBasketParagraphByBasketParagraphId(Long id) {
        return  HibernateSessionFactory.getSessionFactory().openSession().get(BasketParagraph.class, id);
    }

    @Override
    public void update(BasketParagraph bp) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(bp);
        tx1.commit();
        session.close();
    }
}
