package Dao;

import classes.Basket;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class BasketDao implements IBasketDao {

    RowMapper<Basket> mapper = (resultSet, rowNum) -> new Basket(resultSet.getLong("Basket_id"),
            resultSet.getLong("User_id"),
            resultSet.getDouble("Cost"),
            resultSet.getDate("Date_of_purchase"),
            resultSet.getBoolean("Delievered"));

    private final JdbcTemplate jdbcTemplate;

    public BasketDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Basket> getAllBaskets() {return null;
    }

    @Override
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
    public void updateCostOfBasket(Long userId, Double newCost) {
        jdbcTemplate.update("update Baskets set Cost=? where User_id=?", new Object[]{newCost, userId});
    }

    @Override
    public void cleanBasket(Long id) {
        jdbcTemplate.update("delete Baskets where Basket_id=?", new Object[]{id});
    }

    @Override
    public void createBasket(Long id) {
       jdbcTemplate.update("insert into Baskets (User_id) values(?)", new Object[]{id});

    }

    @Override
    public void setDateOfPurchase(Long id, Date time) {
        jdbcTemplate.update("update Baskets set Date_of_Purchase=? where Basket_id=?", new Object[]{time, id});
    }

    @Override
    public Basket getBasketByBasketId(Long basketId) {
        return jdbcTemplate.queryForObject("select * from Basket where Basket_id=? and Date_of_purchase is null", new Object[]{basketId}, mapper);

    }
}
