package Services;

import classes.Basket;

import java.util.Date;
import java.util.List;

public interface IBasketService {

    public void cleanBasket(Long id);

    Basket getBasketByUserId(Long id);

    void updateCostOfBasket(Long userId, Double newCost);


    void createBasket(Long id);

    List<Basket> getOrdersByUserId(Long id);

    void setDateOfPurchase(Long id, Date time);

    Basket getBasketByBasketId(Long basketId);
}
