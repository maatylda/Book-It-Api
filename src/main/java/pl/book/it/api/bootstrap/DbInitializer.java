package pl.book.it.api.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.*;
import pl.book.it.api.model.Dto.UserDto;
import pl.book.it.api.model.room.specifications.RoomType;
import pl.book.it.api.model.user.specifications.Role;
import pl.book.it.api.repositories.*;
import pl.book.it.api.services.user.UserService;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class DbInitializer {

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final TownRepository townRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final UserService userService;

    @EventListener(ContextRefreshedEvent.class)
    public void onEvent() {
        if (userRepository.count() > 0) {
            return;
        }

        User user = getUserFromService(TestConsts.FIRST_NAME_1, TestConsts.LAST_NAME_1, TestConsts.EMAIL_1, TestConsts.PASSWORD_1, TestConsts.PHONE_NUMBER_1);
        User user2 = getUserFromService(TestConsts.FIRST_NAME_1, TestConsts.LAST_NAME_1, TestConsts.EMAIL_2, TestConsts.PASSWORD_1, TestConsts.PHONE_NUMBER_1);
        User user3 = getUserFromService(TestConsts.FIRST_NAME_1, TestConsts.LAST_NAME_1, TestConsts.EMAIL_3, TestConsts.PASSWORD_1, TestConsts.PHONE_NUMBER_1);
        // User user4 = getUserFromService(TestConsts.FIRST_NAME_1, TestConsts.LAST_NAME_1, TestConsts.EMAIL_3, TestConsts.PASSWORD_1, TestConsts.PHONE_NUMBER_1);
        userRepository.saveAll(List.of(user, user2, user3));

        Town town = Town.builder().name(TestConsts.TOWN_NAME_1).build();
        Town town2 = Town.builder().name(TestConsts.TOWN_NAME_2).build();
        townRepository.saveAll(List.of(town, town2));

        Place place = placeBuilder(town, TestConsts.HOTEL_NAME_1, TestConsts.HOTEL_DESCRIPTION_1);
        Place place2 = placeBuilder(town2, TestConsts.HOTEL_NAME_2, TestConsts.HOTEL_DESCRIPTION_2);
        placeRepository.saveAll(List.of(place, place2));

        Room room = roomBuilder(place);
        Room room2 = roomBuilder(place2);
        Room room3 = roomBuilder(place2);
        Room room5 = roomBuilder(place2);
        Room room4 = roomBuilder(place2);
        roomRepository.saveAll(List.of(room, room2, room3, room4, room5));

        Booking booking = bookingBuilder(user, room, TestConsts.BOOKING_DATE_FROM_1, TestConsts.BOOKING_DATE_TO_1);
        Booking booking2 = bookingBuilder(user, room2, TestConsts.BOOKING_DATE_FROM_2, TestConsts.BOOKING_DATE_TO_2);
        Booking booking3 = bookingBuilder(user, room3, TestConsts.BOOKING_DATE_FROM_3, TestConsts.BOOKING_DATE_TO_3);
        Booking booking4 = bookingBuilder(user, room4, TestConsts.BOOKING_DATE_FROM_4, TestConsts.BOOKING_DATE_TO_4);
        Booking booking5 = bookingBuilder(user, room5, TestConsts.BOOKING_DATE_FROM_5, TestConsts.BOOKING_DATE_TO_5);
        bookingRepository.saveAll(List.of(booking, booking2, booking3, booking4, booking5));
    }

    private Place placeBuilder(Town town, String hotelName1, String hotelDescription1) {
        return Place.builder().name(hotelName1).description(hotelDescription1).town(town).build();
    }

    private Booking bookingBuilder(User user, Room room, LocalDate bookingDateFrom1, LocalDate bookingDateTo1) {
        return Booking.builder().dateFrom(bookingDateFrom1).dateTo(bookingDateTo1).room(room).user(user).place(room.getPlace()).build();
    }

    private Room roomBuilder(Place place) {
        return Room.builder().roomType(RoomType.DOUBLE).place(place).price(TestConsts.PRICE_1).build();
    }

    private User getUserFromService(String fName, String lName, String email, String password, String phoneNumb) {
        final UserDto userDto = UserDto.builder().firstName(fName).lastName(lName)
                .email(email).password(password).phoneNumber(phoneNumb).build();
        return userService.createUser(userDto, Role.ADMIN);
    }

}
