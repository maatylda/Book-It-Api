package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.domain.User;
import pl.book.it.api.model.forms.UserForm;
import pl.book.it.api.services.user.UserService;
import pl.book.it.api.services.validation.BookingValidator;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bia/users")
public class UserController {

    private final UserService userService;
    private final BookingValidator bookingValidator;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@Valid @RequestBody final UserForm userForm) {
      if(bookingValidator.userExist(userForm.getEmail()))  {
        return ResponseEntity.badRequest().build();
      }
        final User user = userService.createUser(userForm);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/")
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }
}
