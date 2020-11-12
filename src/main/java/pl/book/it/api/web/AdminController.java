package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.domain.Place;
import pl.book.it.api.domain.Room;
import pl.book.it.api.domain.Town;
import pl.book.it.api.model.forms.PlaceForm;
import pl.book.it.api.model.forms.RoomForm;
import pl.book.it.api.model.forms.TownForm;
import pl.book.it.api.services.TownService;
import pl.book.it.api.services.place.PlaceService;
import pl.book.it.api.services.room.RoomService;
import pl.book.it.api.services.validation.BookingValidator;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bia/admin")
public class AdminController {

    private final PlaceService placeService;
    private final TownService townService;
    private final RoomService roomService;
    private final BookingValidator bookingValidator;

    @PostMapping
    public ResponseEntity<Place> createPlace(@Valid @RequestBody PlaceForm placeForm) throws URISyntaxException {
        if (bookingValidator.placeExistByName(placeForm.getName())) {
            return ResponseEntity.badRequest().build();
        }
        final Place place = placeService.createPlaceFromForm(placeForm);
        return ResponseEntity.created(new URI("/bia/admin/places" + place.getId()))
                .body(place);
    }

    @PostMapping("/towns")
    public ResponseEntity<Town> createTown(@Valid @RequestBody TownForm townForm) throws URISyntaxException {
        if (bookingValidator.townExist(townForm.getName())) {
            return ResponseEntity.badRequest().build();
        }
        final Town town = townService.createTown(townForm.getName());
        return ResponseEntity.created(new URI("/bia/admin/towns" + town.getId()))
                .body(town);
    }

    //roomForm have inside placeId
    //put? or post?
    @PutMapping("/places/{id}")
    public ResponseEntity<Room> createRoomInPlace(@Valid @RequestBody RoomForm roomForm,
                                                  @PathVariable("id") Long placeId) {
        if (bookingValidator.placeExist(placeId)) {
            final Room room = roomService.createRoom(roomForm, placeId);
        }

        return ResponseEntity.badRequest().build();
    }


}
