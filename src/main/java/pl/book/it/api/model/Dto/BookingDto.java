package pl.book.it.api.model.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.book.it.api.annotations.ExistingPlace;
import pl.book.it.api.annotations.ExistingRoom;
import pl.book.it.api.annotations.ExistingUser;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    // jak zwalidować źle wpisaną datę? obsłużyć wyjątek DateTimeParseException?
    // to nie jest sytuacja którą użytkownik powinien dostać na twarz :)
    @NotNull
    private LocalDate dateFrom;

    @NotNull
    private LocalDate dateTo;

    @ExistingUser(message = "api.error.user.not.found")
    private String userEmail;

    @ExistingPlace(message = "api.error.place.not.found")
    private Long placeId;

    @ExistingRoom
    private Long roomId;

    @JsonIgnore
    @AssertTrue(message = "Booking start date need to be before ending date")
    public boolean isDateFromBeforeDateTo() {
        return dateFrom.isBefore(dateTo);
    }
}
