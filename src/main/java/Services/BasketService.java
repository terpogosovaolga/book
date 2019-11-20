package Services;

import Dao.IBasketDao;
import classes.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("BasketService")
public class BasketService implements IBasketService {

    @Autowired
    IBasketDao basketDao;



    public BasketService(IBasketDao bd) {
        basketDao = bd;
    }


    @Override
    public void cleanBasket(Long basketId) {
        basketDao.cleanBasket(basketId);
    }

    @Override
    public Basket getBasketByUserId(Long id) {
        return basketDao.getBasketByUserId(id);
    }

    @Override
    public void updateCostOfBasket(Long userId, Double newCost) {
        basketDao.updateCostOfBasket(userId, newCost);
    }

    @Override
    public void createBasket(Long id) {
       basketDao.createBasket(id);
    }

    @Override
    public List<Basket> getOrdersByUserId(Long id) {
        return basketDao.getOrdersByUserId(id);
    }

    @Override
    public void setDateOfPurchase(Long id, Date time) {
        basketDao.setDateOfPurchase(id, time);
    }

    @Override
    public Basket getBasketByBasketId(Long basketId) {
       return basketDao.getBasketByBasketId(basketId);
    }
}



