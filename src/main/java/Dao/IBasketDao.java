package Dao;

import models.Basket;

import java.util.Date;
import java.util.List;

public interface IBasketDao {

    List<Basket> getAllBaskets(); //returns all baskets and orders of all users from database               SELECT

    Basket getBasketByBasketId(Long basketId); //returns the only one basket with this id from database     SELECT

    void save(Basket basket);  // adds a basket in database                                                 INSERT

    void update(Basket basket);   // updates a basket in database                                           UPDATE

    void setDateOfPurchase(Basket basket, Date date); //makes basket into order making dateOfSale not null  UPDATE

    void delete(Basket basket);   //deletes this basket from database                                       DELETE

    Basket getBasketByUserId(long userId); //returns the only one basket of user with this id               SELECT

    List<Basket> getOrdersByUserId(long userId); //returns orders of this user (date of purchase != null)   SELECT

   // List<Basket> getOrdersByUserId(Long id);

   // void updateCostOfBasket(Long userId, Double newCost);



   // void setDateOfPurchase(Long id, Date time);

}
