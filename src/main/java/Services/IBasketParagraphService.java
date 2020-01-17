package Services;

import models.Basket;
import models.BasketParagraph;

import java.util.List;

public interface IBasketParagraphService {

    void delete(BasketParagraph basketParagraph);
    BasketParagraph save(BasketParagraph bp);
    void update(BasketParagraph bp);
    List<BasketParagraph> getAllBasketParagraphs();
    List<BasketParagraph> getAllBasketParagraphsByBasket(Basket basket);
    BasketParagraph getBasketParagraphByBasketParagraphId(Long id);
    void editNumberOfBooks(BasketParagraph bp, int newNumber);
    BasketParagraph getBasketParagraphByBasketAndBook(long basketId, long bookId);
  //  List<BasketParagraphBooked> getAllBasketParagraphsOfBasket(Long basketId);

  //  void editNumberOfBooks(Long bpId, String plusOrMinus);
   // void editNumberOfBooks(Long bpId, int newNumber);

  //  Double getSumOfBasket(Long id);

 //   void deleteBasketParagraphs(Long id);

  //  BasketParagraph getBasketParagraphByBasketAndBook(Long basketId, Long bookId);

 //   void createBasketParagraph(BasketParagraph basketParagraph);

 //   List<BasketParagraphBooked> getAllBasketParagraphsOfOrders(Long id);

   // List<BasketParagraph> getAllBasketParagraphsWithBook(Long book_id);

  //  void setPrice(Long id, Double cout);
}
