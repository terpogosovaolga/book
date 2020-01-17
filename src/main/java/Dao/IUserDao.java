package Dao;

import models.User;

import java.util.List;


public interface IUserDao {

    List<User> getAllUsers(); // returns list of all users from database             SELECT

    User getUserById(Long id); //returns the only one user with this id              SELECT

    User getUserByUsername(String username); // returns the only one user with this username  SELECT

    void save(User user);  // adds user in database                                  INSERT

    void update(User user);  // updates the data about this user in database         UPDATE

    void delete(User user);  // deletes this user from database                      DELETE

   /* User login(String email, String password);
    Long register(String email, String password);*/
}
