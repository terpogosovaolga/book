package Dao;

import classes.BasketParagraph;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Component
public class BasketParagraphDao  extends JdbcDaoSupport implements IBasketParagraphDao{

    RowMapper<BasketParagraph> mapper = (resultSet, rowNum) -> new BasketParagraph(resultSet.getLong("BasketParagraph_id"),
            resultSet.getLong("Basket_id"),
            resultSet.getLong("Book_id"),
            resultSet.getInt("Count"),
            resultSet.getDouble("Cost"));


    private final JdbcTemplate jdbcTemplate;

    public BasketParagraphDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<BasketParagraph> getAllBasketParagraphs() {
        return null;
    }

}
