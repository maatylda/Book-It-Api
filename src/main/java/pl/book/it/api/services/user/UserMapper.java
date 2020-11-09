package pl.book.it.api.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.User;
import pl.book.it.api.model.forms.UserForm;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Transactional
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User createUserFromForm(UserForm userForm) {
        return User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .email(userForm.getEmail())
                .phoneNumber(userForm.getPhoneNumber())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .bookings(new ArrayList<>())
                .build();
    }
}
