package pl.book.it.api.services.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.book.it.api.model.forms.BookingForm;
import pl.book.it.api.repositories.PlaceRepository;
import pl.book.it.api.repositories.RoomRepository;
import pl.book.it.api.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class Validator {

    private final PlaceRepository placeRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public boolean isBookingFormValid(final BookingForm bookingForm) {
        return chosenDatesValid(bookingForm) &&
                placeExist(bookingForm) &&
                roomExist(bookingForm) &&
                userExist(bookingForm);

    }

    public boolean userExist(final BookingForm bookingForm) {
        return userRepository.findById(bookingForm.getUserEmail()).isPresent();
    }

    public boolean roomExist(final BookingForm bookingForm) {
        return roomRepository.findById(bookingForm.getRoomId()).isPresent();
    }

    public boolean placeExist(final BookingForm bookingForm) {
        return placeRepository.findById(bookingForm.getPlaceId()).isPresent();
    }

    public boolean chosenDatesValid(final BookingForm bookingForm) {
        return bookingForm.getDateFrom().isBefore(bookingForm.getDateTo());
    }


}
