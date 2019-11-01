package Services;

import Dao.IUserDao;
import classes.Basket;
import classes.Book;
import classes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    public UserService(IUserDao u) {userDao=u;}
    @Override
    public Boolean checkCard(String numberOfCard, String name, String cvc) {
        if (numberOfCard.length()!=16) return false;
        if (!name.matches("\\A\\p{ASCII}*\\Z")) return false;
        if (cvc.length()!=4) return false;
        else
            return true;
    }

    @Override
    public List<User> getUsers() {
       return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
       return userDao.getUserById(id);
    }

    @Override
    public User editUser(Long id, Map<String, Object> attrs) {
        User thisUser = userDao.getUserById(id);
        for (Map.Entry<String, Object> attr : attrs.entrySet())
        {
            String key = attr.getKey();
            Object value = attr.getValue();
            switch(key) {
                case ("Имя"):
                    thisUser.editName((String)value);
                    break;
                case ("Фамилия"):
                    thisUser.editFullName((String)value);
                    break;
                case ("Электронная почта"):
                    thisUser.editEmail((String)value);
                    break;
                case ("Пароль"):
                    thisUser.editPassword((String)value);
                    break;
                default:
                    break;
            }
        }

        return userDao.editUser(thisUser);
    }

    /*ПЕРЕНЕСТИ*/
    @Override
    public Basket getBasket(Long userId) {

        return null;
    }
}
