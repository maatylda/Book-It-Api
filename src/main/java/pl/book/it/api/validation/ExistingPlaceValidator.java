package pl.book.it.api.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.book.it.api.annotations.ExistingPlace;
import pl.book.it.api.services.place.PlaceService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ExistingPlaceValidator implements ConstraintValidator<ExistingPlace, Long> {

    private final PlaceService placeService;

    public boolean isValid(Long placeId, ConstraintValidatorContext context) {
        if (!(placeId instanceof Long)) {
            return false;
        }
        if (placeId == null) {
            return false;
        }
        return placeService.placeWithIdExist(placeId);
    }
}
