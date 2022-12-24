package edu.school21.cinema.validation;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
        String message() default "{Password}";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
}
