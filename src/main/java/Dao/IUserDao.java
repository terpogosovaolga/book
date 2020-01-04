package Dao;

import classes.User;

import java.util.List;


public interface IUserDao {


    abstract User getUserByEmail(String email);
    public User editUser(User user);
    public List<User> getAllUsers();
    public User getUserById(Long id);
    public void addUser(User user);

    User login(String email, String password);

    Long register(int accessCode, String email, String name, String fullname, String password);

    long getLastId();

    void deleteUser(Object id);
}
