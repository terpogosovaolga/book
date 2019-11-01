package Services;

import classes.Basket;
import classes.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
    public Boolean checkCard(String numberOfCard, String name, String cvc);
    public List<User> getUsers();
    public User getUserById(Long id);
    public User editUser(Long id, Map<String,Object> attrs);
    public Basket getBasket(Long userId);

}
