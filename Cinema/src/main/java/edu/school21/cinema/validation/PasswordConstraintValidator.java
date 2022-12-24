package edu.school21.cinema.validation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword validPassword) { }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext cxt) {
        if(passwordField == null ||  passwordField.length() < 8) {
            return false;
        }
        String numRegex   = ".*\\d.*";
        String alphaUpperRegex = ".*[A-Z].*";
        String alphaLowerRegex = ".*[a-z].*";
        return (passwordField.matches(numRegex)
                && passwordField.matches(alphaLowerRegex)
                && passwordField.matches(alphaUpperRegex));
    }
}
