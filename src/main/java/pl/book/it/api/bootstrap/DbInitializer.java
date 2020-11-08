package pl.book.it.api.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.*;
import pl.book.it.api.model.specifications.RoomType;
import pl.book.it.api.repositories.*;

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
        User user = User.builder().firstName(DBConstant.FIRST_NAME_1)
                .lastName(DBConstant.LAST_NAME_1)
                .email(DBConstant.EMAIL_1)
                .phoneNumber(DBConstant.PHONE_NUMBER_1)
                .password(DBConstant.PASSWORD_1)
                .bookings(new ArrayList<>())
                .build();

        userRepository.save(user);

        Town town = Town.builder().name(DBConstant.TOWN_NAME_1).build();
        Town town2 = Town.builder().name(DBConstant.TOWN_NAME_2).build();
        townRepository.save(town);
        townRepository.save(town2);

        Place place = Place.builder().name(DBConstant.HOTEL_NAME_1).description(DBConstant.HOTEL_DESCRIPTION_1).town(town).build();
        Place place2 = Place.builder().name(DBConstant.HOTEL_NAME_2).description(DBConstant.HOTEL_DESCRIPTION_2).town(town2).build();
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

        Booking booking = Booking.builder().dateFrom(DBConstant.BOOKING_DATE_FROM_1).dateTo(DBConstant.BOOKING_DATE_TO_1).room(room).user(user).place(room.getPlace()).build();
        Booking booking2 = Booking.builder().dateFrom(DBConstant.BOOKING_DATE_FROM_2).dateTo(DBConstant.BOOKING_DATE_TO_2).room(room2).user(user).place(room2.getPlace()).build();
        Booking booking3 = Booking.builder().dateFrom(DBConstant.BOOKING_DATE_FROM_3).dateTo(DBConstant.BOOKING_DATE_TO_3).room(room3).user(user).place(room3.getPlace()).build();
        Booking booking4 = Booking.builder().dateFrom(DBConstant.BOOKING_DATE_FROM_4).dateTo(DBConstant.BOOKING_DATE_TO_4).room(room4).user(user).place(room4.getPlace()).build();
        Booking booking5 = Booking.builder().dateFrom(DBConstant.BOOKING_DATE_FROM_5).dateTo(DBConstant.BOOKING_DATE_TO_5).room(room5).user(user).place(room5.getPlace()).build();
        bookingRepository.save(booking);
        bookingRepository.save(booking3);
        bookingRepository.save(booking2);
        bookingRepository.save(booking4);
        bookingRepository.save(booking5);
    }

}
