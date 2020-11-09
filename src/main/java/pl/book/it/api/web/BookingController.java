package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.book.it.api.domain.Booking;
import pl.book.it.api.model.forms.BookingForm;
import pl.book.it.api.services.booking.BookingService;
import pl.book.it.api.services.validation.Validator;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bia/users/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final Validator validator;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingForm bookingForm) throws URISyntaxException {
        if (validator.isBookingFormValid(bookingForm)) {
            final Booking booking = bookingService.createBooking(bookingForm);

            return ResponseEntity
                    .created(new URI("/bia/users/bookings" + booking.getId()))
                    .body(booking);

        } else return ResponseEntity.badRequest().build();
    }

}
