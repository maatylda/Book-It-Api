package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.book.it.api.domain.User;
import pl.book.it.api.model.UserForm;
import pl.book.it.api.services.user.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody final UserForm userForm) {
        User createdUser = userService.createUser(userForm);
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }
}
