package pl.book.it.api.services.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Booking;
import pl.book.it.api.domain.Room;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.model.Dto.BookingDto;
import pl.book.it.api.repositories.BookingRepository;
import pl.book.it.api.services.place.PlaceService;
import pl.book.it.api.services.room.RoomService;
import pl.book.it.api.services.user.UserService;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PlaceService placeService;
    private final RoomService roomService;
    private final UserService userService;

    public Booking createBooking(BookingDto bookingDto) {
        final Booking booking = Booking.builder()
                .dateFrom(bookingDto.getDateFrom())
                .dateTo(bookingDto.getDateTo())
                .place(placeService.findPlaceById(bookingDto.getPlaceId()))
                .room(roomService.findRoomById(bookingDto.getRoomId()))
                .isPaid(false)
                .price(calculateBookingPrice(bookingDto))
                .user(userService.findUserById(bookingDto.getUserEmail()))
                .build();

        return bookingRepository.save(booking);
    }

    public Double calculateBookingPrice(BookingDto bookingDto) {
        final long daysBooked = ChronoUnit.DAYS.between(bookingDto.getDateFrom(), bookingDto.getDateTo());
        final Room room = roomService.findRoomById(bookingDto.getRoomId());
        final Double priceForNight = room.getPrice();
        return daysBooked * priceForNight;
    }

    public List<Booking> getAllUsersBookings(String email) {
        return bookingRepository.findAllBookingForUser(email);
    }

    public void deleteBooking(Long id) {
        final Booking booking = doesBookingExist(id);
        bookingRepository.delete(booking);
    }

    public Booking doesBookingExist(Long id) {
        return bookingRepository.findById(id).orElseThrow(() ->
                new BookItException(400, "There is no such booking in our system"));
    }

}
