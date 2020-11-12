package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.domain.User;
import pl.book.it.api.model.Dto.UserDto;
import pl.book.it.api.services.user.UserService;
import pl.book.it.api.services.validation.BookingValidator;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(WebConstants.API_USERS_PATH)
public class UserController {

    private final UserService userService;
    private final BookingValidator bookingValidator;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@Valid @RequestBody final UserDto userDto) {
        if (bookingValidator.userExist(userDto.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        final User user = userService.createUser(userDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/")
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }
}
