package Dao;

import classes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class UserDao implements IUserDao {


    RowMapper<User> mapper = (resultSet, rowNum) -> new User(resultSet.getLong("User_id"),
            resultSet.getInt("Access_code"),
            resultSet.getString("Name"),
            resultSet.getString("Surname"),
            resultSet.getString("Email"),
            resultSet.getString("Password"));

    @Autowired
    private final JdbcTemplate jdbcTemplate;

   @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("select * from Users where Email = ?", new Object[]{email}, mapper);
        } catch (DataAccessException dataAccessException) {
        }
        return user;
    }

    @Override
    public User editUser(User user) {

        assert jdbcTemplate.update("update  Users values (?, ?, ?, ?, ?, ?) where User_id = ?", new Object[]{user.getId(), user.getAccessCode(), user.getName(), user.getFullName(), user.getEmail(), user.getPassword()})>0;
        return getUserById(user.getId());
    }

    @Override
    public List<User> getAllUsers() {

        return jdbcTemplate.query("select * from Users", mapper);
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("select * from Users where User_id = ?", new Object[]{id}, mapper);
        } catch (DataAccessException dataAccessException) {
        }

        return user;

    }

    @Override
    public void addUser(User user) {
        List<User> allUsers = getAllUsers();
        Long id = allUsers.get(allUsers.size()-1).getId(); // самый большой id
        if (user.getId()<id)
        {
            user.setId(id+1);
        }
            assert jdbcTemplate.update("insert into Users values (?, ?, ?, ?, ?, ?)",
                    user.getId(), user.getAccessCode(), user.getName(), user.getFullName(), user.getEmail(), user.getPassword())>0;
        }


    @Override
    public User login(String email, String password) {
        try {
          User user = jdbcTemplate.queryForObject("select * from Users where Email=?", new Object[]{email}, mapper);
          return user;
       }
       catch (EmptyResultDataAccessException e)
       {
           return null;
       }
    }

    @Override
    public String register(int accessCode, String name, String fullname, String email,  String password) {
        try {
            List<User> user = jdbcTemplate.query("select * from Users where Email=?", new Object[]{email}, mapper);
            System.out.println("ID OF THIS ENAIL IS ");
            System.out.println("we have " + user.size() + "mails like that");
            if (user.size()>0)
            {
                System.out.println("we have this email in our base");
                return "Пользователь с таким email уже зарегистрирован";
            }
        }
        catch(EmptyResultDataAccessException e) {
            System.out.println("we have no this email!");
            jdbcTemplate.update("insert into users(Access_code, Name, Surname, Email, Password) values(?, ?, ?, ?, ?)",
                                new Object[]{accessCode, name, fullname,email, password});
            return "Поздравляем! Вы зарегистрированы!";
        }
        return "Поздравляем! Вы зарегистрированы!";
    }

    @Override
    public long getLastId() {
        return jdbcTemplate.queryForObject("select max(User_id) from Users", Long.class);
    }

    @Override
    public void deleteUser(Object id) {
        jdbcTemplate.update("delete  from Users where user_id = ?", new Object[]{id});
    }
}