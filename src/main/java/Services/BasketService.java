package Services;

import Dao.IBasketDao;
import Dao.IBasketParagraphDao;
import models.Basket;
import models.BasketParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("BasketService")
public class BasketService implements IBasketService {

    @Autowired
    IBasketDao basketDao;

    @Autowired
    IBasketParagraphDao basketParagraphDao;


    public BasketService(IBasketDao bd) {
        basketDao = bd;
    }
/*


    @Override
    public Basket getBasketByUserId(Long id) {
        return basketDao.getBasketByUserId(id);
    }

    @Override
    public void updateCostOfBasket(Long basketId, Double newCost) {
        basketDao.updateCostOfBasket(basketId, newCost);
    }



    @Override
    public List<Basket> getOrdersByUserId(Long id) {
        return basketDao.getOrdersByUserId(id);
    }

    @Override
    public void setDateOfPurchase(Long id, Date time) {
        basketDao.setDateOfPurchase(id, time);
    }
}*/

    @Override
    public void delete(Basket basket) {
        List<BasketParagraph> bps = basketParagraphDao.getBasketParagraphsOfBasket(basket);
        for (BasketParagraph bp : bps)
        {
            basketParagraphDao.delete(bp);
        }
        basketDao.delete(basket);

    }

    @Override
    public Basket getBasketByBasketId(long basketId) {
        return basketDao.getBasketByBasketId(basketId);
    }

    @Override
    public void update(Basket basket) {
        //изменить параграфы
        basketDao.update(basket);
    }

    @Override
    public void save(Basket basket) {
        basketDao.save(basket);
    }

    @Override
    public List<Basket> getAllBaskets() {
        return basketDao.getAllBaskets();
    }

    @Override
    public List<Basket> getOrdersByUserId(long userId) {
        return basketDao.getOrdersByUserId(userId);
    }

    @Override
    public Basket getBasketByUserId(long userId) {
        return basketDao.getBasketByUserId(userId);
    }

    @Override
    public void setDateOfPurchase(Basket basket, Date date) {
        basketDao.setDateOfPurchase(basket, date);
    }
}



