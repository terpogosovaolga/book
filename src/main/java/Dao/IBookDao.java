package Dao;

import classes.Book;

import java.util.List;
import java.util.Map;

public interface IBookDao {
    public List<Book> getAllBooks();
    public List<Book> getPopularBooks();

    List<Book> getNewArrivals();

    List<Book> getPoems();

    List<Book> getNovels();

    List<Book> getBooksOfAuthor(String surname);

    List<Book> getBooksOfAuthor(String name, String surname);

    List<Book> getSmallBooks();

    Book getBookById(Long id);
    void admin_addBook(Book book);


    void increaseNumberOfWatchings(Long book_id);

    void updateBook(Book book);

    List<Book> search(String key, String value);

    List<Book> search(Map<String, String> map);
}
