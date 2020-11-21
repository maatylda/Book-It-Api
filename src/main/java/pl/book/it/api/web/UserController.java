package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.annotations.HandledByBookItExceptionHandler;
import pl.book.it.api.domain.User;
import pl.book.it.api.model.Dto.UserDto;
import pl.book.it.api.model.user.specifications.Role;
import pl.book.it.api.services.user.UserService;

import javax.validation.Valid;
import java.security.Principal;

@HandledByBookItExceptionHandler
@RestController
@RequiredArgsConstructor
@RequestMapping(WebConstants.API_USERS_PATH)
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody final UserDto userDto) {
        return userService.createUserWithRole(userDto, Role.USER);
    }

    @GetMapping
    public UserDto showUserDetails(@AuthenticationPrincipal Principal principal) {
        final String email = principal.getName();
        return userService.findUserDtoById(email);
    }

    @DeleteMapping
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }

    @GetMapping("/{email}")
    public UserDto getUserById(@PathVariable String email) {
        return userService.findUserDtoById(email);
    }

    /////////
    @PostMapping("/login")
    public UserDto logIn(@AuthenticationPrincipal Principal principal, String email, String password) {
        final UserDto userDtoById = userService.findUserDtoById(email);
        final String encode = passwordEncoder.encode(password);
        return userDtoById;
    }
}
