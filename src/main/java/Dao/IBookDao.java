package Dao;

import models.Book;

import java.util.List;

public interface IBookDao {
    public List<Book> getAllBooks(); //returns the list of all books in database    SELECT

    Book getBookById(Long id); //returns the only one book with this id             SELECT

    void save(Book book); //adds a book in database                                 INSERT

    void update(Book book);// updates the data about this book                      UPDATE

    void view(Book book); // increases a number of watchings for this book          UPDATE

    void delete(Book book); // deletes a book from database                         DELETE

    List<Book> getPopularBooks();

    List<Book> getNewArrivals();

    List<Book> getPoems();

    List<Book> getNovels();

    List<Book> getSmallBooks();

    List<Book> getBooksOfAuthor(String surname, String name);

    //  public List<Book> getPopularBooks();
  //  List<Book> getNewArrivals();
   // List<Book> getPoems();
   // List<Book> getNovels();
  //  List<Book> getBooksOfAuthor(String surname);
   // List<Book> getBooksOfAuthor(String name, String surname);
   // List<Book> getSmallBooks();
   // void increaseNumberOfWatchings(Long book_id);
   // List<Book> search(String key, String value);
  //  List<Book> search(Map<String, String> map);
}
