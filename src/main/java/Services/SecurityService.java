package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements ISecurityService {

    @Qualifier("authenticationManager")
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public SecurityService(UserDetailsService s) {
        userDetailsService = s;
    }

    @Override
    public String findLoggedInEmail() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }

    @Override
    public void autoLogin(String email, String password) {
        System.out.println(email);
        System.out.println(password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authManager.authenticate(token);
        if (token.isAuthenticated())
        {
            SecurityContextHolder.getContext().setAuthentication(token);

        }
    }
}
