package validators;

import Services.UserService;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Autowired
    private UserService userService;


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if (userService.getUserByEmail(user.getEmail()) != null)
            errors.rejectValue("email", "Duplicate.userForm.email");

        if (!user.getConfirmPassword().equals(user.getPassword()))
        {
            System.out.println(user.getConfirmPassword() + "    " + user.getPassword());
            errors.rejectValue("confirmPassword", "Different.userForm.password");

        }
        if (user.getPassword().length() < 6 || user.getPassword().length() > 32)
            errors.rejectValue("password", "Size.userForm.password");
    }
}
