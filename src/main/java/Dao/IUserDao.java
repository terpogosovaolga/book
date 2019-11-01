package Dao;

import classes.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface IUserDao {


    User getUserByEmail(String email);
    public User editUser(User user);
    public List<User> getAllUsers();
    public User getUserById(Long id);
    public void addUser(User user);
}
