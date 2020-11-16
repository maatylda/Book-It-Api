package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.annotations.HandledByBookItExceptionHandler;
import pl.book.it.api.model.Bookings;
import pl.book.it.api.model.Dto.BookingDto;
import pl.book.it.api.services.booking.BookingService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

@HandledByBookItExceptionHandler
@RestController
@RequiredArgsConstructor
@RequestMapping(WebConstants.API_BOOKINGS_PATH)
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody BookingDto bookingDto) throws URISyntaxException {
        if (bookingService.bookingsForRoomInGivenDates(bookingDto.getDateFrom(), bookingDto.getDateTo(), bookingDto.getRoomId()).size() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            final BookingDto createdBookingDto = bookingService.createBookingAndReturnCreated(bookingDto);
            return ResponseEntity
                    .created(new URI(WebConstants.API_BOOKINGS_PATH + createdBookingDto.getId()))
                    .body(createdBookingDto);
        }
    }

    //to mogłoby być zrobione jako aspekt :)
    @GetMapping
    public Bookings getAllUsersBookings(@RequestParam String email, @AuthenticationPrincipal Principal principal) {
        //zwróci nazwę użytkownika, moze być nullem
        //final Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookingService.getAllUsersBookings(email);
    }

    @DeleteMapping("/{bookingId}")
    public void deleteBooking(@PathVariable final Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }
}
