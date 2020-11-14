package pl.book.it.api.annotations;

import pl.book.it.api.validation.ExistingRoomValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ExistingRoomValidator.class)
@Target({METHOD, CONSTRUCTOR, FIELD})
@Retention(RUNTIME)
public @interface ExistingRoom {
    String message() default "Room does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
