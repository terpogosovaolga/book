package Dao;

import classes.Basket;
import classes.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
/*@Component
public class BasketDao  extends JdbcDaoSupport implements IBasketDao {

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
}*/
