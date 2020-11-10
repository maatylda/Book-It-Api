package pl.book.it.api.services.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Place;
import pl.book.it.api.domain.Town;
import pl.book.it.api.model.forms.PlaceForm;
import pl.book.it.api.services.TownService;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Transactional
public class PlaceMapper {

    private final TownService townService;

    public Place createFromForm(PlaceForm placeForm) {

        return Place.builder()
                .name(placeForm.getName())
                .description(placeForm.getDescription())
                .phoneNumber(placeForm.getPhoneNumber())
                .street(placeForm.getStreet())
                .streetNumber(placeForm.getStreetNumber())
                .zipCode(placeForm.getZipCode())
                .town(getTown(placeForm))
                .pictures(new ArrayList<>())
                .rooms(new ArrayList<>())
                .build();


    }

    private Town getTown(PlaceForm placeForm) {
        return townService.getTownByName(placeForm.getTownName().toUpperCase()).get();
    }

//    private Town getTownFromForm(PlaceForm placeForm) {
//        if(townService.isTownAlreadyExistInDatabase(placeForm.getTownName())){
//             return townService.getTownByName(placeForm.getTownName());
//         }else {
//            //can generate problems
//            return townService.createTown(placeForm.getTownName());
//        }
//    }
}
