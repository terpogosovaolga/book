package Services;

import classes.BasketParagraph;
import classes.BasketParagraphBooked;

import java.util.List;

public interface IBasketParagraphService {

    public void deleteBasketParagraph(Long bpId);

    BasketParagraph createBasketParagraph(Long bookId, Long userId, int number, Double cost);

    List<BasketParagraphBooked> getAllBasketParagraphsOfBasket(Long basketId);

    void editNumberOfBooks(Long bpId, String plusOrMinus);

    Double getSumOfBasket(Long id);

    void deleteBasketParagraphs(Long id);

    BasketParagraph getBasketParagraphByBasketAndBook(Long basketId, Long bookId);

    void createBasketParagraph(BasketParagraph basketParagraph);

    List<BasketParagraphBooked> getAllBasketParagraphsOfOrders(Long id);

    List<BasketParagraph> getAllBasketParagraphsWithBook(Long book_id);

    void setPrice(Long id, Double cout);
}
