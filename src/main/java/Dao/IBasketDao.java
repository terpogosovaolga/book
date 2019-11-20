package Dao;

import classes.Basket;

import java.util.Date;
import java.util.List;

public interface IBasketDao { // вытаскивает корзины из бд
    List<Basket> getAllBaskets();

    Basket getBasketByUserId(Long id);

    List<Basket> getOrdersByUserId(Long id);

    void updateCostOfBasket(Long userId, Double newCost);

    void cleanBasket(Long id);


    void createBasket(Long id);

    void setDateOfPurchase(Long id, Date time);

    Basket getBasketByBasketId(Long basketId);
}
