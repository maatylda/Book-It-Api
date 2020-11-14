package pl.book.it.api.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.book.it.api.annotations.ExistingUser;
import pl.book.it.api.domain.User;
import pl.book.it.api.services.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ExistingUserValidator implements ConstraintValidator<ExistingUser, String> {

    private final UserService userService;

    public void initialize(ExistingUser constraint) {
    }

    @Override
    public boolean isValid(String userEmail, ConstraintValidatorContext constraintValidatorContext) {
       return userEmail!=null && userService.isThereAnAccountWithGivenEmail(userEmail);

    }


}
