package edu.school21.cinema.validation;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
        String message() default "{Email}";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
}
