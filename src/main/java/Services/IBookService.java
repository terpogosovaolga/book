package Services;

import models.Book;

import java.util.List;

public interface IBookService {

    List<Book> getAllBooks();

    Book getBookById(Long id);

    void delete(Book book);

    void save(Book book);

    void update(Book book);

    void view(Book book);

    List<Book> getPopularBooks();

    List<Book> getNewArrivals();

    List<Book> getPoems();

    List<Book> getNovels();

    List<Book> getSmallBooks();

    List<Book> getBooksOfAuthor(String surname, String name);

   // public List<Book> getBooksWithParams(Map<String, String> attrs);
   // public List<Book> getBooksAfterSearch(String search);
   // public List<Book> getPopularBooks();
   // List<Book> getNewArrivals();

  //  List<Book> getPoems();

  //  List<Book> getNovels();

   // List<Book> getSmallBooks();

  //  List<Book> getBooksOfAuthor(String surname);

   // List<Book> getBooksOfAuthor(String name, String surname);

  //  void increaseNumberOfWatchings(Long book_id);



   // List<Book> getBooksWithParams(String key, String value);

    //List<Book> getBooksAfterSearchWithParams(HttpSession session);
}
