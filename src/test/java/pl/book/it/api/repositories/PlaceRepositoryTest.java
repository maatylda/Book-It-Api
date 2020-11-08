package pl.book.it.api.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.book.it.api.domain.*;
import pl.book.it.api.model.specifications.RoomType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlaceRepositoryTest {


    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TownRepository townRepository;
    @Autowired
    private UserRepository userRepository;
    private LocalDate BOOKING_DATE_FROM_1 = LocalDate.of(2020, 11, 1);
    private LocalDate BOOKING_DATE_FROM_2 = LocalDate.of(2020, 11, 3);
    private LocalDate BOOKING_DATE_FROM_3 = LocalDate.of(2020, 10, 30);
    private LocalDate BOOKING_DATE_FROM_4 = LocalDate.of(2020, 11, 5);

    private LocalDate BOOKING_DATE_TO_1 = LocalDate.of(2020, 11, 30);
    private LocalDate BOOKING_DATE_TO_2 = LocalDate.of(2020, 11, 4);
    private LocalDate BOOKING_DATE_TO_3 = LocalDate.of(2020, 11, 7);
    private LocalDate BOOKING_DATE_TO_4 = LocalDate.of(2020, 11, 6);


    @Test
    void shouldfindNoAvailablePlacesInTownInDates(){
        User user = User.builder().firstName("Ann").lastName("Now").email("an@gmail.com").build();
        userRepository.save(user);
        Town town = Town.builder().name("Toruń").build();
        townRepository.save(town);
        Place place = Place.builder().town(town).build();
        placeRepository.save(place);
        Room room = Room.builder().roomType(RoomType.QUAD).place(place).bookings(new ArrayList<>()).build();
        roomRepository.save(room);
        Booking booking = Booking.builder().place(place).user(user).room(room).dateFrom(BOOKING_DATE_FROM_1).dateTo(BOOKING_DATE_TO_1).build();
        Booking booking2 = Booking.builder().place(place).user(user).room(room).dateFrom(BOOKING_DATE_FROM_2).dateTo(BOOKING_DATE_TO_2).build();
        Booking booking3 = Booking.builder().place(place).user(user).room(room).dateFrom(BOOKING_DATE_FROM_3).dateTo(BOOKING_DATE_TO_3).build();
        Booking booking4 = Booking.builder().place(place).user(user).room(room).dateFrom(BOOKING_DATE_FROM_4).dateTo(BOOKING_DATE_TO_4).build();

        bookingRepository.save(booking);
        bookingRepository.save(booking2);
        bookingRepository.save(booking3);
        bookingRepository.save(booking4);


        final List<Place> placesReturned = placeRepository.findPlacesInTownAvaliableInDates(LocalDate.of(2020, 10, 2), LocalDate.of(2020, 12, 3), "Toruń");

        assertThat(placesReturned).isEqualTo(new ArrayList<>());
    }
}