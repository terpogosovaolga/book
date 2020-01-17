package Dao;

import Hibernate.HibernateSessionFactory;
import models.Basket;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
public class BasketDao implements IBasketDao {

    private final JdbcTemplate jdbcTemplate;

    public BasketDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Basket> getAllBaskets() {
        List<Basket> baskets = (List<Basket>)  HibernateSessionFactory.getSessionFactory().openSession().createQuery("From Basket").list();
        return baskets;
    }

    @Override
    public void delete(Basket basket) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(basket);
        tx1.commit();
        session.close();
    }

    @Override
    public Basket getBasketByUserId(long userId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("From Basket where userId =: userId and dateOfSale is null");
        query.setParameter("userId", userId);
        Basket basket = (Basket) query.uniqueResult();
        tx1.commit();
        session.close();
        return basket;
    }

    @Override
    public List<Basket> getOrdersByUserId(long userId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from Basket where userId = :userId and dateOfSale is not null");
        query.setParameter("userId", userId);
        List<Basket> orders = query.list();
        tx.commit();
        session.close();
        return orders;
    }

    @Override
    public void save(Basket basket) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(basket);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Basket basket) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(basket);
        tx1.commit();
        session.close();
    }

    @Override
    public void setDateOfPurchase(Basket basket, Date date) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("update Basket set DateOfSale = :date where basketId = :basketId");
        query.setParameter("date", date);
        query.setParameter("basketId", basket.getId());
        query.executeUpdate();
        tx.commit();
        session.close();
    }

    @Override
    public Basket getBasketByBasketId(Long basketId) {
        return  HibernateSessionFactory.getSessionFactory().openSession().get(Basket.class, basketId);
    }

  /*  @Override
    public Basket getBasketByUserId(Long id) {

        Basket basket;
        try {
            basket = jdbcTemplate.queryForObject("select * from Baskets where User_id=? and Date_of_purchase is null", new Object[]{id}, mapper);
            if (basket.equals(null)) {
                jdbcTemplate.update("insert into Baskets(User_id, Cost) values(?, 0.0)", new Object[]{id});
                basket = jdbcTemplate.queryForObject("select * from Baskets where User_id=? and Date_of_purchase is null", new Object[]{id}, mapper);
            }
            return basket;
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public List<Basket> getOrdersByUserId(Long id) {
        List<Basket> orders = new ArrayList<>();
        try {
             orders = jdbcTemplate.query("select * from Baskets where User_id=? and Date_of_purchase is not null", new Object[]{id}, mapper);
            if (orders.size()!=0) {

                return orders;
            }
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
        return orders;
    }


    @Override
    public void updateCostOfBasket(Long basketId, Double newCost) {
        jdbcTemplate.update("update Baskets set Cost=? where Basket_id=?", new Object[]{newCost, basketId});
    }


    @Override
    public void setDateOfPurchase(Long id, Date time) {
        jdbcTemplate.update("update Baskets set Date_of_Purchase=? where Basket_id=?", new Object[]{time, id});
    }

    @Override
    public Basket getBasketByBasketId(Long basketId) {
        return jdbcTemplate.queryForObject("select * from Baskets where Basket_id=? and Date_of_purchase is null", new Object[]{basketId}, mapper);

    }
   */
}
