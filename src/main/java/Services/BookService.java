package Services;

import Dao.IBookDao;
import classes.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookService implements IBookService {

    @Autowired
   public BookService(IBookDao bd)
  {
     bookDao=bd;
   }

    @Autowired
    IBookDao bookDao;


    @Override
    public List<Book> getBooks() {

        return bookDao.getAllBooks();
    }

    @Override
    public List<Book> getBooksWithParams(Map<String, Object> attrs) {
        List<Book> result = bookDao.getAllBooks();

        int numberOfAttrs = attrs.size();
        for (Book b: result)
        {

        }
        return result;
    }

    @Override
    public List<Book> getBooksAfterSearch(String search) {
        List<Book> allBooks = bookDao.getAllBooks();
        List<Book> result = new ArrayList<>();
        for (Book book : allBooks)
        {

            if (book.getAuthorSureName().contains(search))
                result.add(book);
            else if (book.getName().contains(search))
                result.add(book);
            else if (book.getDescription().contains(search))
                result.add(book);
        }
        return result;
    }

    @Override
    public Book getBookById(Long id) {
        return bookDao.getBookById(id);
    }

    @Override
    public List<Book> getPopularBooks() {
        return bookDao.getPopularBooks();
    }

    @Override
    public List<Book> getNewArrivals() {
        return bookDao.getNewArrivals();
    }

    @Override // перенести к BasketParagraph
    public void addBookToBasket(Long bookId, Integer number, Long userId) {

    }

    @Override
    public void admin_addBook(Book book) {
        System.out.println("ФАМИЛИЯ АВТОРА: " + book.getAuthorSureName());
        bookDao.admin_addBook(book);
    }

    @Override
    public void increaseNumberOfWatchings(Long book_id) {
        bookDao.increaseNumberOfWatchings(book_id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
}
