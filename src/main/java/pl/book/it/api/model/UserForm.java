package pl.book.it.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@NoArgsConstructor
public class UserForm {

    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String phoneNumber;
    @Length(min = 8)
    private String password;
    @Email
    private String email;

    private LocalDate birthDate;


}
