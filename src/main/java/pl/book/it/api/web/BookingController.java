package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.annotations.HandledByBookItExceptionHandler;
import pl.book.it.api.domain.Booking;
import pl.book.it.api.model.Bookings;
import pl.book.it.api.model.Dto.BookingDto;
import pl.book.it.api.services.booking.BookingService;
import pl.book.it.api.services.room.RoomService;

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
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingDto bookingDto) throws URISyntaxException {
        if (bookingService.bookingsForRoomInGivenDates(bookingDto.getDateFrom(), bookingDto.getDateTo(), bookingDto.getRoomId()).size() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            final Booking booking = bookingService.createBooking(bookingDto);
            return ResponseEntity
                    .created(new URI(WebConstants.API_BOOKINGS_PATH + booking.getId()))
                    .body(booking);
        }
    }

    //to mogłoby być zrobione jako aspekt :)
    @GetMapping
    public Bookings getAllUsersBookings(@RequestBody String email, @AuthenticationPrincipal Principal principal) {
        //zwróci nazwę użytkownika, moze być nullem
        //final Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new Bookings(bookingService.getAllUsersBookings(email));
    }

    @DeleteMapping("/{bookingId}")
    public void deleteBooking(@PathVariable final Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }

}
