package pl.book.it.api.mappers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.book.it.api.bootstrap.TestConsts;
import pl.book.it.api.domain.*;
import pl.book.it.api.model.Dto.BookingDto;
import pl.book.it.api.model.room.specifications.RoomStandard;
import pl.book.it.api.model.room.specifications.RoomType;
import pl.book.it.api.repositories.*;
import pl.book.it.api.web.AbstractSpringTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class BookingMapperTest extends AbstractSpringTest {

    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private TownRepository townRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void shouldMapBookingToBookingDto() {
        User user = userRepository.save(
                User.builder().firstName(TestConsts.FIRST_NAME_1)
                        .lastName(TestConsts.LAST_NAME_1)
                        .phoneNumber(TestConsts.PHONE_NUMBER_1)
                        .email(TestConsts.EMAIL_1)
                        .password(TestConsts.PASSWORD_1)
                        .build());
        Town town = townRepository.save(
                Town.builder()
                        .name("Warszawa")
                        .build());
        Place place = placeRepository.save(
                Place.builder()
                        .name("Test Hotel")
                        .description("Super hotel")
                        .town(town)
                        .phoneNumber("12345645")
                        .streetNumber("Wiejska")
                        .streetNumber("100")
                        .zipCode("00-000")
                        .build());
        Room room = roomRepository.save(
                Room.builder()
                        .roomType(RoomType.QUAD)
                        .standard(RoomStandard.DELUXE)
                        .place(place)
                        .price(1500.55)
                        .build());
        Booking booking = bookingRepository.save(
                Booking.builder()
                        .user(user)
                        .place(place)
                        .user(user)
                        .room(room)
                        .dateFrom(LocalDate.of(2020,12,10))
                        .dateTo(LocalDate.of(2020,12,15))
                        .build());

        final BookingDto bookingDto = bookingMapper.toBookingDto(booking);

        assertThat(bookingDto.getDateFrom()).isEqualTo(LocalDate.of(2020,12,10));
        assertThat(bookingDto.getDateTo()).isEqualTo(LocalDate.of(2020,12,15));
        assertThat(bookingDto.getPlaceId()).isEqualTo(place.getId());
        assertThat(bookingDto.getRoomId()).isEqualTo(room.getId());
        assertThat(bookingDto.getUserEmail()).isEqualTo(TestConsts.EMAIL_1);
        assertThat(bookingDto.getId()).isPositive();
    }

}