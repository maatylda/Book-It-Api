package pl.book.it.api.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.User;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.mappers.UserMapStructMapper;
import pl.book.it.api.model.Dto.UserDto;
import pl.book.it.api.model.user.specifications.Role;
import pl.book.it.api.repositories.UserRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapStructMapper userMapStructMapper;
    private final PasswordEncoder passwordEncoder;

    public boolean accountWithEmailExists(String email) {
        return userRepository.findById(email).isPresent();
    }

    public User findUserById(String email) {
        return userRepository.findById(email).orElseThrow(() ->
                new BookItException("There is no user with email: " + email,"email"));
    }
    public UserDto findUserDtoById(String email) {
        return userMapStructMapper.toUserDto(findUserById(email));
    }

    public User createUser(UserDto userDto, Role role) {
        if (accountWithEmailExists(userDto.getEmail())) {
            throw new BookItException(String.format("There is already user with email: %s, chose another email.", userDto.getEmail()), "email");
        }
        final User user = userMapStructMapper.toUser(userDto);
        user.setBookings(new ArrayList<>());
        encodeAndSetPassword(userDto, user);
        setRoleForUser(role, user);
        return userRepository.save(user);
    }

    private void encodeAndSetPassword(UserDto userDto, User user) {
        final String password = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(password);
    }

    public UserDto createUserWithRole(UserDto userDto, Role role) {
        final User userSaved = createUser(userDto, role);
        return userMapStructMapper.toUserDto(userSaved);
    }

    public void setRoleForUser(Role roleForUser, User user) {
        user.setRole(roleForUser);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
