package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.annotations.HandledByBookItExceptionHandler;
import pl.book.it.api.domain.User;
import pl.book.it.api.model.Dto.UserDto;
import pl.book.it.api.model.user.specifications.Role;
import pl.book.it.api.services.user.UserService;

import javax.validation.Valid;

@HandledByBookItExceptionHandler
@RestController
@RequiredArgsConstructor
@RequestMapping(WebConstants.API_USERS_PATH)
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody final UserDto userDto) {
        return userService.createUserWithRole(userDto, Role.USER);
    }

    @DeleteMapping
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }
}
