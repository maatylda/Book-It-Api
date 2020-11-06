package pl.book.it.api.services.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.book.it.api.domain.User;
import pl.book.it.api.model.UserForm;
import pl.book.it.api.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User createUser(UserForm userForm) {
        User user = userMapper.createUserFromForm(userForm);
        userRepository.save(user);
        return user;
    }


}
