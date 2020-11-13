package pl.book.it.api.services.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.User;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.exceptions.NoUserWithGivenIdException;
import pl.book.it.api.model.Dto.UserDto;
import pl.book.it.api.model.user.specifications.Role;
import pl.book.it.api.repositories.UserRepository;
import pl.book.it.api.services.validation.BookingValidator;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BookingValidator bookingValidator;

    public boolean isThereAnAccountWithGivenEmail(String email) {
        return userRepository.findById(email).isPresent();
    }

    public User findUserById(String email) {
        if (isThereAnAccountWithGivenEmail(email)) {
            return userRepository.getOne(email);
        }
        throw new NoUserWithGivenIdException("There is no user with email: " + email);
    }


    public User createUser(UserDto userDto, Role role) {
        if (isThereAnAccountWithGivenEmail(userDto.getEmail())) {
            throw new BookItException(400, String.format("There is already user with email: %s, chose another email.", userDto.getEmail()), 101);
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
