package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.domain.Booking;
import pl.book.it.api.model.Bookings;
import pl.book.it.api.model.forms.BookingForm;
import pl.book.it.api.services.booking.BookingService;
import pl.book.it.api.services.validation.BookingValidator;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bia/users/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final BookingValidator bookingValidator;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingForm bookingForm) throws URISyntaxException {
        if (bookingValidator.isBookingFormValid(bookingForm)) {
            final Booking booking = bookingService.createBooking(bookingForm);

            return ResponseEntity
                    .created(new URI("/bia/users/bookings" + booking.getId()))
                    .body(booking);

        } else return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public Bookings getAllUsersBookings (@RequestBody String email){
      return  new Bookings(bookingService.getAllUsersBookings(email)) ;

    }

}
