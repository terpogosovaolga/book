package Dao;

import classes.BasketParagraph;
import classes.BasketParagraphBooked;

import java.util.List;

public interface IBasketParagraphDao {
    BasketParagraph createBasketParagraph(Long bookId, Long userId, int number, Double cost);


    List<BasketParagraphBooked> getAllBasketParagraphsOfBasket(Long basketId);

    Double getSumOfBasket(Long id);

    void deleteBasketParagraphs(Long basketId);

    void deleteBasketParagraph(Long bpId);

    BasketParagraph getBasketParagraphByBasketAndBook(Long basketId, Long bookId);

    void plusNumberOfBooks(Long bpId);

    void minusNumberOfBooks(Long bpId);

    List<BasketParagraphBooked> getAllBasketParagraphsOfOrder(Long id);

    List<BasketParagraph> getAllBasketParagraphsWithBook(Long book_id);

    void setPrice(Long id, Double cout);

    void editNumberOfBooks(Long bpId, int newNumber);
}
