package Dao;

import Dao.IUserDao;
import classes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.*;
import java.util.UUID;
public class UserDao  extends JdbcDaoSupport implements IUserDao {


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

        assert jdbcTemplate.update("update  Users values (?, ?, ?, ?, ?, ?) where User_id = ?1", user.getId(), user.getAccessCode(), user.getName(), user.getFullName(), user.getEmail(), user.getPassword())>0;

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

    }
