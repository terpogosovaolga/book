package Dao;

import classes.Book;

import java.util.List;

public interface IBookDao {
    public List<Book> getAllBooks();
    public List<Book> getPopularBooks();
}
