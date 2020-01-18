import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class MultiHttpSecurityConfig {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .anyRequest().hasRole("ADMIN")
                    .and()
                    .csrf().disable()
                    .httpBasic();
        }
    }

    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("login?error")
                    .permitAll()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/admin/**")
                    .access("hasRole('ADMIN')")
                    .antMatchers("/book/**", "/basket/**", "catalog/**", "/user, /login**, /**")
                    .access("hasAnyRole('ROLE_USER' ,'ROLE_ADMIN', 'ROLE_ANONYMOUS')")
                    .antMatchers("/orders/**", "/user/**")
                    .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                    .and()
                    .csrf().disable()
                    .httpBasic();
        }
    }
}