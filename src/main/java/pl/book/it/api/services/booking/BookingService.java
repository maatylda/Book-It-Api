package pl.book.it.api.services.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Booking;
import pl.book.it.api.domain.Place;
import pl.book.it.api.domain.Room;
import pl.book.it.api.domain.User;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.model.Dto.BookingDto;
import pl.book.it.api.repositories.BookingRepository;
import pl.book.it.api.services.place.PlaceService;
import pl.book.it.api.services.room.RoomService;
import pl.book.it.api.services.user.UserService;

import java.time.LocalDate;
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
        final User userFromBookingDto = getUserFromBookingDto(bookingDto);
        final Room roomFromBookingDto = getRoomFromBookingDto(bookingDto);
        final Place placeFromBookingDto = getPlaceFromBookingDto(bookingDto);
        final Booking booking = Booking.builder()
                .dateFrom(bookingDto.getDateFrom())
                .dateTo(bookingDto.getDateTo())
                .place(placeFromBookingDto)
                .room(roomFromBookingDto)
                .isPaid(false)
                .price(calculateBookingPrice(bookingDto))
                .user(userFromBookingDto)
                .build();
        userService.updateUser(userFromBookingDto);
        roomService.updateRoom(roomFromBookingDto);
        return bookingRepository.save(booking);
    }

    public Place getPlaceFromBookingDto(BookingDto bookingDto) {
        return placeService.findPlaceById(bookingDto.getPlaceId());
    }

    public Room getRoomFromBookingDto(BookingDto bookingDto) {
        return roomService.findRoomById(bookingDto.getRoomId());
    }

    public User getUserFromBookingDto(BookingDto bookingDto) {
        return userService.findUserById(bookingDto.getUserEmail());
    }

    public Double calculateBookingPrice(BookingDto bookingDto) {
        final long daysBooked = ChronoUnit.DAYS.between(bookingDto.getDateFrom(), bookingDto.getDateTo());
        final Room room = getRoomFromBookingDto(bookingDto);
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

    public List<Booking> bookingsForRoomInGivenDates(LocalDate chosenDateFrom, LocalDate chosenDateTo, Long roomId) {
        return bookingRepository.findAllBookingsForRoomInDates(chosenDateFrom, chosenDateTo, roomId);
    }

}
