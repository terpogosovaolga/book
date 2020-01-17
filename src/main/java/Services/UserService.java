package Services;

import Dao.IRoleDao;
import Dao.IUserDao;
import models.Role;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(IUserDao u, IRoleDao r, BCryptPasswordEncoder e) {userDao=u; roleDao = r; encoder= e;}


    @Override
    public List<User> getUsers() {
       return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
       return userDao.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByUsername(email);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getRoleById(1L));
        user.setRoles(roles);
        userDao.save(user);
    }
/*
    @Override
    public User login(String email, String password) {
        return userDao.login(email, password);
    }


    @Override
    public Long register(int accessCode, String email, String name, String fullname, String password) {
        return userDao.register(accessCode,  name, fullname, email, password);
    }
*/
/*
    @Override
    public long getNumberOfUsers() {
        return userDao.getLastId();
    }


    @Override
    public User getUser(String email) {
        return userDao.getUserByEmail(email);
    }
*/
}
