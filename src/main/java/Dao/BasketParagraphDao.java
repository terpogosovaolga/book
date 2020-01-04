package Dao;

import classes.Basket;
import classes.BasketParagraph;
import classes.BasketParagraphBooked;
import classes.Book;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class BasketParagraphDao implements IBasketParagraphDao{

    RowMapper<BasketParagraph> mapper = (resultSet, rowNum) -> new BasketParagraph(resultSet.getLong("BasketParagraph_id"),
            resultSet.getLong("Basket_id"),
            resultSet.getLong("Book_id"),
            resultSet.getInt("Count"),
            resultSet.getDouble("Cost"));

    RowMapper<Book> bookMapper = (resultSet, rowNum) -> new Book(resultSet.getLong("Book_id"),
            resultSet.getString("Author_name"),
            resultSet.getString("Author_secondname"),
            resultSet.getString("Author_surname"),
            resultSet.getString("Book_name"),
            resultSet.getInt("Year_of_writing"),
            resultSet.getString("Publisher"),
            resultSet.getInt("Year_of_publishing"),
            resultSet.getString("Translater"),
            resultSet.getInt("Pages_number"),
            resultSet.getString("Genre"),
            resultSet.getString("Original_language"),
            resultSet.getString("Language"),
            resultSet.getDouble("Price"),
            resultSet.getInt("Count"),
            resultSet.getString("Description"),
            resultSet.getInt("Number_of_watchings"));

    RowMapper<Basket> basketMapper = (resultSet, rowNum) -> new Basket(resultSet.getLong("Basket_id"),
            resultSet.getLong("User_id"),
            resultSet.getDouble("Cost"),
            resultSet.getDate("Date_of_purchase"),
            resultSet.getBoolean("Delievered"));


    private final JdbcTemplate jdbcTemplate;

    public BasketParagraphDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
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
    public void deleteBasketParagraph(Long bpId) {
        jdbcTemplate.update("delete from BasketParagraphs where BasketParagraph_id = ?", new Object[]{bpId});
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
    }

    @Override
    public void editNumberOfBooks(Long bpId, int newNumber) {
        jdbcTemplate.update("update BasketParagraphs set Count=? where BasketParagraph_id=?", new Object[]{newNumber, bpId});
        BasketParagraph bp = jdbcTemplate.queryForObject("select * from BasketParagraphs where BasketParagraph_id=?", new Object[]{bpId}, mapper);
        Double price = jdbcTemplate.queryForObject("select Price from Books where Book_id=?", new Object[]{bp.getBookId()}, Double.class);

        setPrice(bpId, price*newNumber);

    }

}
