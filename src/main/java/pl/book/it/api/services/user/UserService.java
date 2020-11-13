package pl.book.it.api.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.User;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.model.ApiErrors;
import pl.book.it.api.model.Dto.UserDto;
import pl.book.it.api.model.user.specifications.Role;
import pl.book.it.api.repositories.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public boolean isThereAnAccountWithGivenEmail(String email) {
        return userRepository.findById(email).isPresent();
    }

    public User findUserById(String email) {
        return userRepository.findById(email).orElseThrow(() ->
                new BookItException("There is no user with email: " + email, ApiErrors.USER_NOT_FOUND.getCode()));
    }

    public User createUser(UserDto userDto, Role role) {
        if (isThereAnAccountWithGivenEmail(userDto.getEmail())) {
            throw new BookItException(String.format("There is already user with email: %s, chose another email.", userDto.getEmail()), 101);
        }
        User user = userMapper.createUser(userDto);
        userMapper.setRoleForUser(role, user);
        userRepository.save(user);
        return user;
    }

    public void updateUser() {
        //todo
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
