package classes;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;

public class User {


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
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getName() {return name;}
    public void setId(Long id) { this.id = id;}
    public void editName(String value){
        name = value;
    }
    public void editFullName(String value){
        fullName = value;
    }
    public void editEmail(String value) {
        email = value;
    }
    public void editPassword(String value)
    { password = value; }
}
