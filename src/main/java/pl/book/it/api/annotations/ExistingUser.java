package pl.book.it.api.annotations;

import pl.book.it.api.validation.ExistingUserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ExistingUserValidator.class)
@Target({ METHOD, CONSTRUCTOR,FIELD})
@Retention(RUNTIME)
public @interface ExistingUser {

    String message() default "User must be from database";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
