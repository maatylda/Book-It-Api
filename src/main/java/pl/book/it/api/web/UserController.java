package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.domain.User;
import pl.book.it.api.model.UserForm;
import pl.book.it.api.services.user.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bia/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody final UserForm userForm) {
        return userService.createUser(userForm);
    }

    @DeleteMapping("/")
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }
}
