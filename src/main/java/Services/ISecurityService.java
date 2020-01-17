package Services;

public interface ISecurityService {
    String findLoggedInEmail();
    void autoLogin(String email, String password);
}
