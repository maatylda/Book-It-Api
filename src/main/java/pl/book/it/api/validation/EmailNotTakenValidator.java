package pl.book.it.api.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.book.it.api.annotations.EmailNotTaken;
import pl.book.it.api.repositories.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class EmailNotTakenValidator implements ConstraintValidator<EmailNotTaken, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findById(email).isEmpty();
    }
}
