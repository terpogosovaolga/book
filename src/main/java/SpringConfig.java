import Dao.BookDao;
import Dao.IBookDao;
import Dao.IUserDao;
import Dao.UserDao;
import Services.BookService;
import Services.IBookService;
import Services.IUserService;
import Services.UserService;
import classes.Book;
import com.test.pluto.BookController;
import com.test.pluto.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {


   /* @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }*/

   /* @Bean("dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/Books_Shop");
        dataSource.setUsername("user");
        dataSource.setPassword("password");
        dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        return dataSource;
    }*/
    /*<bean id="dataSource"
    class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
        <property name="url" value="jdbc:mysql://localhost:3306/Books_Shop" />
        <property name="username" value="root" />
        <property name="password" value="root" />
    </bean>*/

   /* @Bean("userDao")
    public IUserDao getUserDao() {
        return new UserDao(getJdbcTemplate());
    }

    @Bean("userService")
    public IUserService getUserService() {
        return new UserService(getUserDao());
    }

    @Bean("bookDao")
    public IBookDao getBookDao() {
        return new BookDao(getJdbcTemplate());
    }

    @Bean("bookService")
    public IBookService getBookService() {
        return new BookService(getBookDao());
    }

    @Bean("bookController")
    public BookController getBookController() {
        return new BookController(getBookService());
    }
    @Bean("userController")
    public UserController getUserController() {
        return new UserController(getUserService());
    }

    public void main(String[] args){
        UserController userController = new UserController(getUserService());
        BookController bookController = new BookController(getBookService());
    }*/
}
