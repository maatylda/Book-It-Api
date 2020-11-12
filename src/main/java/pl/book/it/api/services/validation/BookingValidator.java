package pl.book.it.api.services.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.book.it.api.model.forms.BookingForm;
import pl.book.it.api.repositories.PlaceRepository;
import pl.book.it.api.repositories.RoomRepository;
import pl.book.it.api.repositories.TownRepository;
import pl.book.it.api.repositories.UserRepository;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class BookingValidator {

    private final PlaceRepository placeRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final TownRepository townRepository;

    public boolean isBookingFormValid(final BookingForm bookingForm) {
        return chosenDatesValid(bookingForm.getDateFrom(), bookingForm.getDateTo()) &&
                placeExist(bookingForm.getPlaceId()) &&
                roomExist(bookingForm.getRoomId()) &&
                userExist(bookingForm.getUserEmail());

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

    public boolean placeExistByName(final String placeName) {
        return placeRepository.findPlacesByName(placeName).isPresent();
    }

    public boolean chosenDatesValid(final LocalDate dateFrom, final LocalDate dateTo) {
        return dateFrom.isBefore(dateTo);
    }

    public boolean townExist(final String townName) {
        return townRepository.findByName(townName).isPresent();
    }


}
