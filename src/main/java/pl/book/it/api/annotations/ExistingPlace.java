package pl.book.it.api.annotations;

import pl.book.it.api.validation.ExistingPlaceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ExistingPlaceValidator.class)
@Target({METHOD, CONSTRUCTOR, FIELD})
@Retention(RUNTIME)
public @interface ExistingPlace {
    String message() default "Place does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
