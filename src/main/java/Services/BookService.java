package Services;

import Dao.IBookDao;
import classes.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
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
        for (Map.Entry<String, Object> attr : attrs.entrySet())
        {
            String key = attr.getKey();
            Object value = attr.getValue();
            switch(key) {
                case ("Жанр"):
                    for (Book book : result)
                    {
                        if (book.getGenre() != value)
                            result.remove(book);
                    }
                    break;
                case ("Издательство"):
                    for (Book book : result)
                    {
                        if (book.getPublisher() != value)
                            result.remove(book);
                    }
                    break;
                case ("Язык"):
                    if (value!="Язык оригинала") {
                        for (Book book : result) {
                            if (book.getLanguageOrOriginalLanguage() != value)
                                result.remove(book);
                        }
                    }
                    else{
                        for (Book book : result) {
                            if (book.translated()) // false = не переведено
                            result.remove(book);
                        }
                    }
                    break;
                case ("Цена"):
                    for (Book book : result) {
                        if (book.getCout() != value)
                            result.remove(book);
                    }
                    break;
                default:
                    break;
            }
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
        List<Book> allBooks = bookDao.getAllBooks();
        for (Book book:allBooks)
        {

            if (book.getId()==id)
                return book;
        }
        return null;
    }

    @Override
    public List<Book> getPopularBooks() {
       List<Book> books = bookDao.getPopularBooks();
       for(Book b : books)
       {
           System.out.println("POPULAR BOOKS IS : *******************************" + b.getName() + " " + b.getNumberOfWatching());
       }
       return books;
    }

    @Override
    public void addBookToBasket(Long bookId, Integer number, Long userId) {

    }
}
