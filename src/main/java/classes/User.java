package classes;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.lang.reflect.Field;
import java.sql.Date;

public class User {


    public User(Long id){
        this.id=id;
    }

    public User(){}

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public User(long id,int accessCode, String name, String fullName, String email, String password)
    {
        this.id = id;
        this.accessCode = accessCode;
        this.name = name;
        this.fullName = fullName;
        this.email = email;
        this.password = password;

    }
    @NotNull
    Long id;


    @NotNull
    int accessCode;

    @NotNull
    String name;

    @NotNull
    String fullName;

    @NotNull
    String email;

    @NotNull
    String password;

    public Long getId() {
        return id;
    }
    public int getAccessCode() {return accessCode;}
    public String getFullName() {return fullName;}
    @ModelAttribute("email")
    public void setEmail(String email) {this.email=email;}
    public String getEmail() {return email;}
    @ModelAttribute("password")
    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return password;}
    public String getName() {return name;}
    public void setId(Long id) { this.id = id;}
    @ModelAttribute("name")
    public void setName(String value){
        name = value;
    }
    @ModelAttribute("fullName")
    public void setFullName(String value){
        fullName = value;
    }
    public void universalSet(String key, Object value) { // присваивает значение value полю key
        Field field;
        try {
            field = User.class.getField(key);
            field.set(this, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
