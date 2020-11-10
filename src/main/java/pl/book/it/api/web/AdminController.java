package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.domain.Place;
import pl.book.it.api.domain.Room;
import pl.book.it.api.model.Rooms;
import pl.book.it.api.model.forms.PlaceForm;
import pl.book.it.api.services.place.PlaceService;
import pl.book.it.api.services.validation.BookingValidator;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bia/admin")
public class AdminController {

    private final PlaceService placeService;
    private final BookingValidator bookingValidator;

    @PostMapping
    public ResponseEntity<Place> createPlace(@Valid @RequestBody PlaceForm placeForm) throws URISyntaxException {
        if (bookingValidator.placeExistByName(placeForm.getName())) {
            return ResponseEntity.badRequest().build();
        }
        final Place place = placeService.createPlaceFromForm(placeForm);
        return ResponseEntity.created(new URI("/bia/admin/places"+ place.getId()))
                .body(place);
    }

    /*@PutMapping("/places/{id}")
    public ResponseEntity<List<Room>> addRoomsToPlace (@PathVariable("id") Long placeId,
                                                       @RequestBody Rooms rooms){
        if (bookingValidator.placeExist(placeId)){

        }

    }*/


}
