package pl.book.it.api.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.User;
import pl.book.it.api.model.Dto.UserDto;
import pl.book.it.api.model.user.specifications.Role;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Transactional
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User createUser(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .bookings(new ArrayList<>())
                .build();
    }

    public void setRoleForUser(Role roleForUser, User user) {
        user.setRole(roleForUser);
    }
}
