package Services;

import classes.Basket;
import classes.User;

import java.util.List;

public interface IUserService {
    public List<User> getUsers();
    public User getUserById(Long id);

    User editUser(User user);

    User login(String email, String password);

    public Basket getBasket(Long userId);

    Long register(int accessCode, String email, String name, String fullname, String password);

    long getNumberOfUsers();

    void deleteUser(Object anonId);
}
