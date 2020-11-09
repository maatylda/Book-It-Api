package pl.book.it.api.services.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class PlaceMapper {

    /*private final TownService townService;

    public Place createFromForm(PlaceForm placeForm){

        return Place.builder()
                    .name(placeForm.getName())
                    .description(placeForm.getDescription())
                    .phoneNumber(placeForm.getPhoneNumber())
                    .street(placeForm.getStreet())
                    .streetNumber(placeForm.getStreetNumber())
                    .zipCode(placeForm.getZipCode())
                    .town(townService.getTownByName(placeForm.getTownName()))
        .build();


    }

    private Town getTownFromForm(PlaceForm placeForm) {
        if(townService.isTownAlreadyExistInDatabase(placeForm.getTownName())){
             return townService.getTownByName(placeForm.getTownName());
         }else {
            //can generate problems
            return townService.createTown(placeForm.getTownName());
        }
    }*/
}
