package pl.book.it.api.services.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Booking;
import pl.book.it.api.domain.Room;
import pl.book.it.api.model.forms.BookingForm;
import pl.book.it.api.repositories.BookingRepository;
import pl.book.it.api.repositories.PlaceRepository;
import pl.book.it.api.repositories.RoomRepository;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PlaceRepository placeRepository;
    private final RoomRepository roomRepository;

    public Booking createBooking(BookingForm bookingForm) {
        final Booking booking = Booking.builder()
                .dateFrom(bookingForm.getDateFrom())
                .dateTo(bookingForm.getDateTo())
                .place(placeRepository.getOne(bookingForm.getPlaceId()))
                .room(roomRepository.getOne(bookingForm.getRoomId()))
                .isPaid(false)
                .price(bookingPrice(bookingForm))
                .build();

        return bookingRepository.save(booking);
    }

    public Double bookingPrice(BookingForm bookingForm) {
        final long daysBooked = ChronoUnit.DAYS.between(bookingForm.getDateFrom(), bookingForm.getDateTo());
        final Room room = roomRepository.getOne(bookingForm.getRoomId());
        final Double priceForNight = room.getPrice();

        return daysBooked * priceForNight;

    }

    public List<Booking> getAllUsersBookings (String email){
       return bookingRepository.findAllBookingForUser(email);
    }


}
