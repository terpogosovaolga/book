package Services;

import models.Basket;

import java.util.Date;
import java.util.List;

public interface IBasketService {

    void delete(Basket basket);

    Basket getBasketByBasketId(long basketId);

    void update(Basket basket);

    void save(Basket basket);

    List<Basket> getAllBaskets();

    List<Basket> getOrdersByUserId(long userId);

    Basket getBasketByUserId(long userId);

    void setDateOfPurchase(Basket basket, Date date);

    //void updateCostOfBasket(Long userId, Double newCost);


    //List<Basket> getOrdersByUserId(Long id);

    //void setDateOfPurchase(Long id, Date time);


}
