package Services;

import Dao.IUserDao;
import classes.Basket;
import classes.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Autowired
    public UserService(IUserDao u) {userDao=u;}


    @Override
    public List<User> getUsers() {
       return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
       return userDao.getUserById(id);
    }

    @Override
    public User editUser(User user) {
        return userDao.editUser(user);
    }

    @Override
    public User login(String email, String password) {
        return userDao.login(email, password);
    }

    /*ПЕРЕНЕСТИ*/
    @Override
    public Basket getBasket(Long userId) {

        return null;
    }

    @Override
    public String register(int accessCode, String email, String name, String fullname, String password) {
        return userDao.register(accessCode,  name, fullname, email, password);
    }

    @Override
    public long getNumberOfUsers() {
        return userDao.getLastId();
    }

    @Override
    public void deleteUser(Object id) {
         userDao.deleteUser(id);
    }
}
