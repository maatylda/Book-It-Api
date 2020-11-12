package pl.book.it.api.services.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Booking;
import pl.book.it.api.domain.Room;
import pl.book.it.api.model.Dto.BookingDto;
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

    public Booking createBooking(BookingDto bookingDto) {
        final Booking booking = Booking.builder()
                .dateFrom(bookingDto.getDateFrom())
                .dateTo(bookingDto.getDateTo())
                .place(placeRepository.getOne(bookingDto.getPlaceId()))
                .room(roomRepository.getOne(bookingDto.getRoomId()))
                .isPaid(false)
                .price(calculateBookingPrice(bookingDto))
                .build();

        return bookingRepository.save(booking);
    }

    public Double calculateBookingPrice(BookingDto bookingDto) {
        final long daysBooked = ChronoUnit.DAYS.between(bookingDto.getDateFrom(), bookingDto.getDateTo());
        final Room room = roomRepository.getOne(bookingDto.getRoomId());
        final Double priceForNight = room.getPrice();

        return daysBooked * priceForNight;

    }

    public List<Booking> getAllUsersBookings (String email){
       return bookingRepository.findAllBookingForUser(email);
    }


}
