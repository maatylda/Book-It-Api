package pl.book.it.api.services.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Place;
import pl.book.it.api.domain.Town;
import pl.book.it.api.model.Dto.PlaceDto;
import pl.book.it.api.services.TownService;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Transactional
public class PlaceMapper {

    private final TownService townService;

    public Place createFromForm(PlaceDto placeDto) {

        return Place.builder()
                .name(placeDto.getName())
                .description(placeDto.getDescription())
                .phoneNumber(placeDto.getPhoneNumber())
                .street(placeDto.getStreet())
                .streetNumber(placeDto.getStreetNumber())
                .zipCode(placeDto.getZipCode())
                .town(getTown(placeDto))
                .pictures(new ArrayList<>())
                .rooms(new ArrayList<>())
                .build();


    }

    private Town getTown(PlaceDto placeDto) {
        return townService.getTownByName(placeDto.getTownName().toUpperCase()).get();
    }

//    private Town getTownFromForm(PlaceDto placeForm) {
//        if(townService.isTownAlreadyExistInDatabase(placeForm.getTownName())){
//             return townService.getTownByName(placeForm.getTownName());
//         }else {
//            //can generate problems
//            return townService.createTown(placeForm.getTownName());
//        }
//    }
}
