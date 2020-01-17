package Dao;

import models.Basket;
import models.BasketParagraph;
import models.Book;

import java.util.List;

public interface IBasketParagraphDao {

    List<BasketParagraph> getAllBasketParagraphs();  //returns the list of all basket paragraphs of all baskets         SELECT

    BasketParagraph getBasketParagraphByBasketParagraphId(Long id); //returns the only one basket paragraph by its id   SELECT

    BasketParagraph save(BasketParagraph basketParagraph); //adds a basketParagraph in database. if it's exist, returns it  INSERT

    void update(BasketParagraph bp);   //updates the data about this basket paragraph in database                       UPDATE

    void delete(BasketParagraph basketParagraph);       // deletes this basket paragraph from database                  DELETE

    List<BasketParagraph> getBasketParagraphsOfBook(Book book);  //returns the list of basketParagraphs with this book  SELECT

    List<BasketParagraph> getBasketParagraphsOfBasket(Basket basket); //returns the list of basketParagraphs of this basket SELECT

    BasketParagraph getBasketParagraphByBasketAndBook(long basketId, long bookId);   //returns the only one basketParagraph in this basket of this book SELECT

    void updateSum(Double differenceBetweenOldAndNewPrice, BasketParagraph bp); //updates sum of this basketParagraph   UPDATE

    void updateNumberOfBooks(int plusNumber, BasketParagraph bp); //increases the number of books on plusNumber         UPDATE

   // List<BasketParagraphBooked> getAllBasketParagraphsOfBasket(Long basketId);

    //Double getSumOfBasket(Long id);

   // void deleteBasketParagraphs(Long basketId);

    //BasketParagraph getBasketParagraphByBasketAndBook(Long basketId, Long bookId);

   // void plusNumberOfBooks(Long bpId);

 //   void minusNumberOfBooks(Long bpId);

  //  List<BasketParagraphBooked> getAllBasketParagraphsOfOrder(Long id);

   // List<BasketParagraph> getAllBasketParagraphsWithBook(Long book_id);

   // void setPrice(Long id, Double cout);

    //void editNumberOfBooks(Long bpId, int newNumber);
}
