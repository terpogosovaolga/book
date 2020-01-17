package Services;

import models.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    void update(User user);

    void delete(User user);

    void save(User user);

   // User login(String email, String password);

   // Long register(String email, String password);

  //  long getNumberOfUsers();

   //User getUser(String email);

}
