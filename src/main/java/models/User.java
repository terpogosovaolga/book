package models;

import Services.SecurityService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {


    public User(Long id){
        this.id=id;
    }

    public User() {}

    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public User(long id, String email, String password)
    {
        this.id = id;
        this.email = email;
        this.password = password;

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    Long id;

    @Column(name = "username")
    @NotNull
    String email;

    @Column(name = "password")
    @NotNull
    String password;

    @Transient
    private String confirmPassword;

    @ManyToMany
    @JoinTable(name = "Users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    @ModelAttribute("email")
    public void setEmail(String email) {this.email=email;}

    public String getEmail() {return email;}

    @ModelAttribute("password")
    public void setPassword(String password) {this.password = password;}

    public String getPassword() {return password;}

    public void setId(Long id) { this.id = id;}

    public boolean equals( User user ){
        if(user==null) return false;
        if(user==this)
            return true;
        if (user.getId() != getId()) return false;
        if (user.getPassword() != user.getPassword()) return false;
        return (user.getEmail()==this.getEmail());
    }

    public String toString() {
        return this.getId() + ": \n email:" + getEmail() + "\n password: " + getPassword() + "\n conf-password: " + getConfirmPassword();
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
