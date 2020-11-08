package pl.book.it.api.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.book.it.api.domain.User;
import pl.book.it.api.model.UserForm;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User createUserFromForm(UserForm userForm) {
        return User.builder().firstName(userForm.getName()).lastName(userForm.getLastName()).email(userForm.getEmail())
                .phoneNumber(userForm.getPhoneNumber()).password(passwordEncoder.encode(userForm.getPassword()))
                .birthDate(userForm.getBirthDate()).bookings(new ArrayList<>()).build();
    }
}
