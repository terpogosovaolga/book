package Dao;

import classes.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

public class BookDao implements IBookDao {


    RowMapper<Book> mapper = (resultSet, rowNum) -> new Book(resultSet.getLong("Book_id"),
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

    @Override
    public Book getBookById(Long id) {
        return jdbcTemplate.queryForObject("Select * from Books where Book_id = ?", new Object[]{id}, mapper);

    }

    @Override
    public void admin_addBook(Book book) {
         jdbcTemplate.update("insert into books(Author_name, Author_surname, Book_name, Year_of_writing, Publisher, Year_of_publishing, " +
                                "Original_language, Price, Count, Genre)" +
                "        values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new Object[]{book.getAuthorName(), book.getAuthorSureName(),
                            book.getName(), book.getYearOfWriting(), book.getPublisher(), book.getYearOfPublishing(), book.getOriginalLanguage(),
                            book.getCout(), book.getCount(), book.getGenre()});
         long index = jdbcTemplate.queryForObject("select max(book_id) from Books", Long.class);

         if (!book.getAuthorSecondName().equals(null)) {
             jdbcTemplate.update("update Books set Author_secondName=? where book_id=?", new Object[]{book.getAuthorSecondName(), index});
         }

        if (!book.getDescription().equals(null)) {
            jdbcTemplate.update("update Books set Description=? where book_id=?", new Object[]{book.getDescription(), index});
        }

        if (book.getCountOfPages()!=0) {
            jdbcTemplate.update("update Books set Pages_number=? where book_id=?", new Object[]{book.getCountOfPages(), index});
        }
        if (!book.getTranslater().equals(null)) {
            jdbcTemplate.update("update Books set Translater=? where book_id=?", new Object[]{book.getTranslater(), index});
        }

        if (!book.getGenre().equals(null)) {
            jdbcTemplate.update("update Books set Genre=? where book_id=?", new Object[]{book.getGenre(), index});
        }


    }

    @Override
    public void increaseNumberOfWatchings(Long book_id) {
        int views = jdbcTemplate.queryForObject("select Number_of_watchings  from Books where Book_id=?", new Object[]{book_id}, Integer.class);
        jdbcTemplate.update("update Books set Number_of_watchings=? where book_id=?", new Object[]{views+1, book_id});
    }

    @Override
    public void updateBook(Book book){
        jdbcTemplate.update("update Books set Author_Name=?, " +
                        "Author_SecondName=?, " +
                        "Author_SurName=?, " +
                        "Book_Name=?," +
                "Year_of_writing=?, " +
                        "Publisher=?, " +
                        "Year_of_publishing=?, " +
                        "Translater=?, " +
                        "Pages_number=?, " +
                        "Genre=?, " +
                "Original_language=?, " +
                        "Language=?, " +
                        "Price=?, " +
                        "Count=?, " +
                        "Description=?, " +
                        "Number_of_watchings=? " +
                        "where Book_id=?",
                new Object[]{book.getAuthorName(),
                        book.getAuthorSecondName(),
                        book.getAuthorSureName(),
                        book.getName(),
                book.getYearOfWriting(),
                        book.getPublisher(),
                        book.getYearOfPublishing(),
                        book.getTranslater(),
                        book.getCountOfPages(),
                book.getGenre(),
                        book.getOriginalLanguage(),
                        book.getLanguage(),
                        book.getCout(),
                        book.getCount(),
                book.getDescription(),
                        book.getNumberOfWatching(),
                        book.getId()});

    }

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

}
