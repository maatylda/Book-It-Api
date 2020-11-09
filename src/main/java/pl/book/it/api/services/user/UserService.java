package pl.book.it.api.services.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.User;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.exceptions.NoUserWithGivenIdException;
import pl.book.it.api.model.forms.UserForm;
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
        if (isThereAnAccountWithGivenEmail(email)) {
            return userRepository.getOne(email);
        }
        throw new NoUserWithGivenIdException("There is no user with email: " + email);
    }


    public User createUser(UserForm userForm) {
        if (isThereAnAccountWithGivenEmail(userForm.getEmail())) {
            throw new BookItException(String.format("There is already user with email: %s, chose another email.", userForm.getEmail()));
        }
        User user = userMapper.createUserFromForm(userForm);
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
