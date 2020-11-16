package pl.book.it.api.services.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Booking;
import pl.book.it.api.domain.Room;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.mappers.BookingMapper;
import pl.book.it.api.model.Bookings;
import pl.book.it.api.model.Dto.BookingDto;
import pl.book.it.api.repositories.BookingRepository;
import pl.book.it.api.services.room.RoomService;
import pl.book.it.api.services.user.UserService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    private final UserService userService;
    private final BookingMapper bookingMapper;

    public Booking createBooking(BookingDto bookingDto) {
        final Booking booking = bookingMapper.toBooking(bookingDto);
        final Double bookingPrice = calculateBookingPrice(booking);
        booking.setPrice(bookingPrice);
        booking.setPaid(false);
        userService.saveUser(booking.getUser());
        roomService.updateRoom(booking.getRoom());
        return bookingRepository.save(booking);
    }

    public BookingDto createBookingAndReturnCreated(BookingDto bookingDto) {
        return bookingMapper.toBookingDto(createBooking(bookingDto));
    }

    public Double calculateBookingPrice(Booking booking) {
        final long daysBooked = ChronoUnit.DAYS.between(booking.getDateFrom(), booking.getDateTo());
        final Room room = booking.getRoom();
        final Double priceForNight = room.getPrice();
        return daysBooked * priceForNight;
    }

    public Bookings getAllUsersBookings(String email) {
        return new Bookings(bookingRepository.findAllBookingForUser(email).stream()
                .map(bookingMapper::toBookingDto)
                .collect(Collectors.toList()));
    }

    public void deleteBooking(Long id) {
        final Booking booking = findBookingIfItExist(id);
        bookingRepository.delete(booking);
    }

    public Booking findBookingIfItExist(Long id) {
        return bookingRepository.findById(id).orElseThrow(() ->
                new BookItException(400, "There is no such booking in our system"));
    }

    public List<Booking> bookingsForRoomInGivenDates(LocalDate chosenDateFrom, LocalDate chosenDateTo, Long roomId) {
        return bookingRepository.findAllBookingsForRoomInDates(chosenDateFrom, chosenDateTo, roomId);
    }
}
