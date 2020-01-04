package Services;

import classes.Book;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface IBookService {
    public List<Book> getBooks();
    public List<Book> getBooksWithParams(Map<String, String> attrs);
    public List<Book> getBooksAfterSearch(String search);
    public Book getBookById(Long id);
    public List<Book> getPopularBooks();
    List<Book> getNewArrivals();

    List<Book> getPoems();

    List<Book> getNovels();

    List<Book> getSmallBooks();

    List<Book> getBooksOfAuthor(String surname);

    List<Book> getBooksOfAuthor(String name, String surname);

    //public void deleteBookById(Long id);
    public void addBookToBasket(Long bookId, Integer number, Long userId);


    void admin_addBook(Book book);

    void increaseNumberOfWatchings(Long book_id);

    void updateBook(Book book);

    List<Book> getBooksWithParams(String key, String value);

    //List<Book> getBooksAfterSearchWithParams(HttpSession session);
}
