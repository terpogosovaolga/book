package Dao;

import classes.Book;

import java.util.List;

public interface IBookDao {
    public List<Book> getAllBooks();
    public List<Book> getPopularBooks();

    List<Book> getNewArrivals();

    Book getBookById(Long id);
    void admin_addBook(Book book);


    void increaseNumberOfWatchings(Long book_id);

    void updateBook(Book book);
}
