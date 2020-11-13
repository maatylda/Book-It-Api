package pl.book.it.api.services.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.model.ApiErrors;
import pl.book.it.api.model.Dto.BookingDto;
import pl.book.it.api.repositories.PlaceRepository;
import pl.book.it.api.repositories.RoomRepository;
import pl.book.it.api.repositories.TownRepository;
import pl.book.it.api.repositories.UserRepository;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class BookingValidator {
    //during refactoring

    private final PlaceRepository placeRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final TownRepository townRepository;

    public boolean isBookingDtoValid(final BookingDto bookingDto) {
        checkThatTheBookingDatesAreCorrect(bookingDto.getDateFrom(), bookingDto.getDateTo());
        return placeExist(bookingDto.getPlaceId()) &&
                roomExist(bookingDto.getRoomId()) &&
                userExist(bookingDto.getUserEmail());
    }

    public boolean userExist(final String userEmail) {
        return userRepository.findById(userEmail).isPresent();
    }

    public boolean roomExist(final Long roomId) {
        return roomRepository.findById(roomId).isPresent();
    }

    public boolean placeExist(final Long placeId) {
        return placeRepository.findById(placeId).isPresent();
    }

    public boolean checkIfPlaceExistByName(final String placeName) {
        return placeRepository.findPlacesByName(placeName).isPresent();
    }

    public void checkThatTheBookingDatesAreCorrect(final LocalDate dateFrom, final LocalDate dateTo) {
        if (!dateFrom.isBefore(dateTo)) {
            throw new BookItException("Booking start date need to be before ending date", ApiErrors.BOOKING_CREATION_FAILED.getCode());
        }
    }

    public void checkIfTheTownExist(final String townName) {
        townRepository.findByName(townName).orElseThrow(() ->
                new BookItException(String.format("There is no such Town as %s in database", townName), ApiErrors.TOWN_NOT_FOUND.getCode()));
    }

}
