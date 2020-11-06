package pl.book.it.api.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.*;
import pl.book.it.api.domain.specifications.RoomType;
import pl.book.it.api.reposietories.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Transactional
public class DbInitializer {

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final TownRepository townRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void onEvent() {
        User user = User.builder().name("Anna").lastName("Kowalska").email("akow@akow.com").phoneNumber("123123123").password("qwerty").bookings(new ArrayList<>()).build();
        userRepository.save(user);

        Town town = Town.builder().name("GDYNIA").build();
        Town town2 = Town.builder().name("SOPOT").build();
        townRepository.save(town);
        townRepository.save(town2);

        Place place = Place.builder().name("Hotel1").description("testHotel").town(town).build();
        Place place2 = Place.builder().name("Hotel2").description("testHotel2").town(town).build();
        placeRepository.save(place);
        placeRepository.save(place2);

        Room room = Room.builder().numberOfGuests(RoomType.DOUBLE).place(place).build();
        Room room2 = Room.builder().numberOfGuests(RoomType.DOUBLE).place(place2).build();
        Room room3 = Room.builder().numberOfGuests(RoomType.DOUBLE).place(place2).build();
        Room room5 = Room.builder().numberOfGuests(RoomType.DOUBLE).place(place2).build();
        Room room4 = Room.builder().numberOfGuests(RoomType.DOUBLE).place(place2).build();
        roomRepository.save(room);
        roomRepository.save(room2);
        roomRepository.save(room3);
        roomRepository.save(room4);
        roomRepository.save(room5);

        Booking booking = Booking.builder().dateFrom(LocalDate.of(2020, 11, 1)).dateTo(LocalDate.of(2020, 11, 5)).room(room).user(user).place(room.getPlace()).build();
        Booking booking2 = Booking.builder().dateFrom(LocalDate.of(2020, 11, 3)).dateTo(LocalDate.of(2020, 11, 4)).room(room2).user(user).place(room.getPlace()).build();
        Booking booking3 = Booking.builder().dateFrom(LocalDate.of(2020, 10, 30)).dateTo(LocalDate.of(2020, 11, 7)).room(room3).user(user).place(room.getPlace()).build();
        Booking booking4 = Booking.builder().dateFrom(LocalDate.of(2020, 11, 5)).dateTo(LocalDate.of(2020, 11, 6)).room(room4).user(user).place(room.getPlace()).build();
        Booking booking5 = Booking.builder().dateFrom(LocalDate.of(2020, 10, 30)).dateTo(LocalDate.of(2020, 11, 2)).room(room5).user(user).place(room.getPlace()).build();
        bookingRepository.save(booking);
        bookingRepository.save(booking3);
        bookingRepository.save(booking2);
        bookingRepository.save(booking4);
        bookingRepository.save(booking5);
    }

}