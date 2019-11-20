package Services;

import Dao.IBasketParagraphDao;
import classes.BasketParagraph;
import classes.BasketParagraphBooked;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BasketParagraphService")

public class BasketParagraphService implements IBasketParagraphService {

    IBasketParagraphDao myBp;

    public BasketParagraphService(IBasketParagraphDao bpd) {
        myBp = bpd;
    }
    @Override
    public void deleteBasketParagraph(Long bpId) {
        myBp.deleteBasketParagraph(bpId);
    }

    @Override
    public BasketParagraph createBasketParagraph(Long bookId, Long basketId, int number, Double cost) {

        return myBp.createBasketParagraph(bookId, basketId, number, cost*number);
    }

    @Override
    public List<BasketParagraphBooked> getAllBasketParagraphsOfBasket(Long basketId) {
        return myBp.getAllBasketParagraphsOfBasket(basketId);
    }

    @Override
    public void editNumberOfBooks(Long  bpId, String plusOrMinus) {
        if (plusOrMinus.equals("plus"))
        {
            myBp.plusNumberOfBooks(bpId);
        }
        if (plusOrMinus.equals("minus"))
        {
            myBp.minusNumberOfBooks(bpId);
        }
    }

    @Override
    public Double getSumOfBasket(Long id) {
        return myBp.getSumOfBasket(id);
    }

    @Override
    public void deleteBasketParagraphs(Long basketId) {
        myBp.deleteBasketParagraphs(basketId);
    }

    @Override
    public BasketParagraph getBasketParagraphByBasketAndBook(Long basketId, Long bookId) {
        return myBp.getBasketParagraphByBasketAndBook(basketId, bookId);
    }

    @Override
    public void createBasketParagraph(BasketParagraph bp) {
        createBasketParagraph(bp.getBookId(), bp.getBasketId(), bp.getNumberOfBooks(), bp.getSum());
    }

    @Override
    public List<BasketParagraphBooked> getAllBasketParagraphsOfOrders(Long id) {
        return myBp.getAllBasketParagraphsOfOrder(id);
    }

    @Override
    public List<BasketParagraph> getAllBasketParagraphsWithBook(Long book_id) {
        return myBp.getAllBasketParagraphsWithBook(book_id);
    }

    @Override
    public void setPrice(Long id, Double cout) {
        myBp.setPrice(id, cout);
    }
}
