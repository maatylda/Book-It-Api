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
        final Booking savedBooking = bookingMapper.toBooking(bookingDto);
        savedBooking.setPrice(calculateBookingPrice(bookingDto));
        savedBooking.setPaid(false);
        return bookingRepository.save(savedBooking);
    }

    public BookingDto createBookingAndReturnCreated(BookingDto bookingDto) {
        final Booking booking = createBooking(bookingDto);
        return bookingMapper.toBookingDto(booking);
    }

    public Double calculateBookingPrice(BookingDto bookingDto) {
        final long daysBooked = ChronoUnit.DAYS.between(bookingDto.getDateFrom(), bookingDto.getDateTo());
        final Room room = roomService.findRoomById(bookingDto.getRoomId());
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
                new BookItException("There is no booking with given id","bookingId"));
    }

    public List<Booking> bookingsForRoomInGivenDates(LocalDate chosenDateFrom, LocalDate chosenDateTo, Long roomId) {
        return bookingRepository.findAllBookingsForRoomInDates(chosenDateFrom, chosenDateTo, roomId);
    }

    public BookingDto findBookingDtoById(Long id) {
      return bookingMapper.toBookingDto(findBookingIfItExist(id));
    }
}
