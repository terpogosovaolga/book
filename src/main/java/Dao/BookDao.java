package Dao;

import classes.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
public class BookDao extends JdbcDaoSupport implements IBookDao {


    RowMapper<Book> mapper = (resultSet, rowNum) -> new Book(resultSet.getLong("Book_id"),
            resultSet.getString("Author_name"),
            resultSet.getString("Author_secondname"),
            resultSet.getString("Author_surname"),
            resultSet.getString("Book_name"),
            resultSet.getInt("Year_of_writing"),
            resultSet.getString("Publisher"),
            resultSet.getInt("Year_of_publishhing"),
            resultSet.getString("Translater"),
            resultSet.getInt("Pages_number"),
            resultSet.getString("Genre"),
            resultSet.getString("Original_language"),
            resultSet.getString("Language"),
            resultSet.getDouble("Price"),
            resultSet.getInt("Count"),
            resultSet.getString("Description"),
            resultSet.getInt("Number_of_watchings"));

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private BookDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query("select * from Books", mapper);
    }

    @Override
    public List<Book> getPopularBooks() {

        System.out.println("you entered in dao");
        List<Book> popularBooks = jdbcTemplate.query("select * from Books where Number_of_watchings > 2000", mapper);;
        System.out.println("you are in getPopularBooks() of BookDao: popular books are: ");
        for (Book b : popularBooks)
            System.out.println(b.getName());
        return popularBooks;
    }


}
