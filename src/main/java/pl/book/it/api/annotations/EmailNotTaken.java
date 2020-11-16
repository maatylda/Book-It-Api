package pl.book.it.api.annotations;

import pl.book.it.api.validation.EmailNotTakenValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = EmailNotTakenValidator.class)
@Target({METHOD, CONSTRUCTOR, FIELD})
@Retention(RUNTIME)
public @interface EmailNotTaken {
    String message() default "Email is already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
