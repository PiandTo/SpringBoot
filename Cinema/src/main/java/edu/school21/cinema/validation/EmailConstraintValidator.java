package edu.school21.cinema.validation;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, String> {
    private UserService userService;

    @Autowired
    public EmailConstraintValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(ValidEmail validEmail) {

    }

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext cxt) {
        return (userService.findUserByEmail(emailField) == null);
    }
}
