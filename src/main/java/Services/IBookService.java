package Services;

import classes.Book;

import java.util.List;
import java.util.Map;

public interface IBookService {
    public List<Book> getBooks();
    public List<Book> getBooksWithParams(Map<String, Object> attrs);
    public List<Book> getBooksAfterSearch(String search);
    public Book getBookById(Long id);
    public List<Book> getPopularBooks();
    //public void addBook(Map<String,Object> attrs);
    //public void deleteBookById(Long id);
    public void addBookToBasket(Long bookId, Integer number, Long userId);

}
