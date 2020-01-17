package Services;

import Dao.IBasketDao;
import Dao.IBasketParagraphDao;
import models.Basket;
import models.BasketParagraph;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BasketParagraphService")
public class BasketParagraphService implements IBasketParagraphService {

    IBasketParagraphDao myBp;

    IBasketDao myBasket;

    public BasketParagraphService(IBasketParagraphDao bpd, IBasketDao b) {
        myBp = bpd;
        myBasket = b;
    }

  /*

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

    public void editNumberOfBooks(Long bpId, int newNumber){
        myBp.editNumberOfBooks(bpId, newNumber);
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
*/
    @Override
    public void delete(BasketParagraph basketParagraph) {
        myBp.delete(basketParagraph);

       // изменить сумму баскета
        // myBaket.setSum();
    }

    @Override
    public BasketParagraph save(BasketParagraph bp) {
        return myBp.save(bp);
        // изменить сумму баскета
        // myBaket.setSum();
    }

    @Override
    public void update(BasketParagraph bp) {
        myBp.update(bp);
        // изменить сумму баскета
        // myBaket.setSum();
    }

    @Override
    public List<BasketParagraph> getAllBasketParagraphs() {
        return myBp.getAllBasketParagraphs();
    }

    @Override
    public List<BasketParagraph> getAllBasketParagraphsByBasket(Basket basket) {
        return myBp.getBasketParagraphsOfBasket(basket);
    }

    @Override
    public BasketParagraph getBasketParagraphByBasketParagraphId(Long id) {
        return myBp.getBasketParagraphByBasketParagraphId(id);
    }

    @Override
    public void editNumberOfBooks(BasketParagraph bp, int newNumber) {
        Double price = bp.getSum()/bp.getNumberOfBooks();
        bp.setNumberOfBooks(newNumber);
        bp.setSum(price * newNumber);
        update(bp);
    }

    @Override
    public BasketParagraph getBasketParagraphByBasketAndBook(long basketId, long bookId) {
        return myBp.getBasketParagraphByBasketAndBook(basketId, bookId);
    }

}
