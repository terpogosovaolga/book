package Dao;

import classes.Basket;

import java.util.List;

public interface IBasketDao { // вытаскивает корзины из бд
    List<Basket> getAllBaskets();
}
