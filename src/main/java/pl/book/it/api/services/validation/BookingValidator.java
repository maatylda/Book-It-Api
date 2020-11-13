package pl.book.it.api.services.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.model.ApiErrors;
import pl.book.it.api.model.Dto.BookingDto;
import pl.book.it.api.services.place.PlaceService;
import pl.book.it.api.services.room.RoomService;
import pl.book.it.api.services.user.UserService;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class BookingValidator {

    private final PlaceService placeService;
    private final RoomService roomService;
    private final UserService userService;

    public void isBookingDtoValid(final BookingDto bookingDto) {
        checkThatTheBookingDatesAreCorrect(bookingDto.getDateFrom(), bookingDto.getDateTo());
        userService.findUserById(bookingDto.getUserEmail());
        roomService.getRoomById(bookingDto.getRoomId());
        placeService.findPlaceById(bookingDto.getPlaceId());
    }

    public void checkThatTheBookingDatesAreCorrect(final LocalDate dateFrom, final LocalDate dateTo) {
        if (!dateFrom.isBefore(dateTo)) {
            throw new BookItException("Booking start date need to be before ending date", ApiErrors.BOOKING_CREATION_FAILED.getCode());
        }
    }

}
