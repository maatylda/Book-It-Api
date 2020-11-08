package pl.book.it.api.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.*;
import pl.book.it.api.domain.specifications.RoomType;
import pl.book.it.api.repositories.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Transactional
public class DbInitializer {

    private static final String PHONE_NUMBER_1 = "123123123";
    private static final String FIRST_NAME_1 = "Anna";
    private static final String LAST_NAME_1 = "Kowalska";
    private static final String EMAIL_1 = "akow@akow.com";
    private static final String PASSWORD_1 = "qwerty";
    private static final String TOWN_NAME_1 = "GDYNIA";
    private static final String TOWN_NAME_2 = "SOPOT";
    private static final String HOTEL_NAME_1 = "Hotel1";
    private static final String HOTEL_NAME_2 = "Hotel2";
    private static final String HOTEL_DESCRIPTION_1 = "testHotel";
    private static final String HOTEL_DESCRIPTION_2 = "testHotel2";
    private static final LocalDate BOOKING_DATE_FROM_1 = LocalDate.of(2020, 11, 1);
    private static final LocalDate BOOKING_DATE_FROM_2 = LocalDate.of(2020, 11, 3);
    private static final LocalDate BOOKING_DATE_FROM_3 = LocalDate.of(2020, 10, 30);
    private static final LocalDate BOOKING_DATE_FROM_4 = LocalDate.of(2020, 11, 5);
    private static final LocalDate BOOKING_DATE_FROM_5 = LocalDate.of(2020, 10, 30);
    private static final LocalDate BOOKING_DATE_TO_1 = LocalDate.of(2020, 11, 5);
    private static final LocalDate BOOKING_DATE_TO_2 = LocalDate.of(2020, 11, 4);
    private static final LocalDate BOOKING_DATE_TO_3 = LocalDate.of(2020, 11, 7);
    private static final LocalDate BOOKING_DATE_TO_4 = LocalDate.of(2020, 11, 6);
    private static final LocalDate BOOKING_DATE_TO_5 = LocalDate.of(2020, 11, 2);

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final TownRepository townRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;



    @EventListener(ContextRefreshedEvent.class)
    public void onEvent() {
        User user = User.builder().firstName(FIRST_NAME_1)
                .lastName(LAST_NAME_1)
                .email(EMAIL_1)
                .phoneNumber(PHONE_NUMBER_1)
                .password(PASSWORD_1)
                .bookings(new ArrayList<>())
                .build();
        
        userRepository.save(user);

        Town town = Town.builder().name(TOWN_NAME_1).build();
        Town town2 = Town.builder().name(TOWN_NAME_2).build();
        townRepository.save(town);
        townRepository.save(town2);

        Place place = Place.builder().name(HOTEL_NAME_1).description(HOTEL_DESCRIPTION_1).town(town).build();
        Place place2 = Place.builder().name(HOTEL_NAME_2).description(HOTEL_DESCRIPTION_2).town(town2).build();
        placeRepository.save(place);
        placeRepository.save(place2);

        Room room = Room.builder().roomType(RoomType.DOUBLE).place(place).build();
        Room room2 = Room.builder().roomType(RoomType.DOUBLE).place(place2).build();
        Room room3 = Room.builder().roomType(RoomType.DOUBLE).place(place2).build();
        Room room5 = Room.builder().roomType(RoomType.DOUBLE).place(place2).build();
        Room room4 = Room.builder().roomType(RoomType.DOUBLE).place(place2).build();
        roomRepository.save(room);
        roomRepository.save(room2);
        roomRepository.save(room3);
        roomRepository.save(room4);
        roomRepository.save(room5);

        Booking booking = Booking.builder().dateFrom(BOOKING_DATE_FROM_1).dateTo(BOOKING_DATE_TO_1).room(room).user(user).place(room.getPlace()).build();
        Booking booking2 = Booking.builder().dateFrom(BOOKING_DATE_FROM_2).dateTo(BOOKING_DATE_TO_2).room(room2).user(user).place(room2.getPlace()).build();
        Booking booking3 = Booking.builder().dateFrom(BOOKING_DATE_FROM_3).dateTo(BOOKING_DATE_TO_3).room(room3).user(user).place(room3.getPlace()).build();
        Booking booking4 = Booking.builder().dateFrom(BOOKING_DATE_FROM_4).dateTo(BOOKING_DATE_TO_4).room(room4).user(user).place(room4.getPlace()).build();
        Booking booking5 = Booking.builder().dateFrom(BOOKING_DATE_FROM_5).dateTo(BOOKING_DATE_TO_5).room(room5).user(user).place(room5.getPlace()).build();
        bookingRepository.save(booking);
        bookingRepository.save(booking3);
        bookingRepository.save(booking2);
        bookingRepository.save(booking4);
        bookingRepository.save(booking5);
    }

}
