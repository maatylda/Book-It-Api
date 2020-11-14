package pl.book.it.api.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.book.it.api.annotations.ExistingRoom;
import pl.book.it.api.services.room.RoomService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ExistingRoomValidator implements ConstraintValidator<ExistingRoom, Long> {

    private final RoomService roomService;

    public boolean isValid(Long roomId, ConstraintValidatorContext context) {
        if (!(roomId instanceof Long)) {
            return false;
        }
        if (roomId == null) {
            return false;
        }
        return roomService.roomWithIdExist(roomId);
    }
}
