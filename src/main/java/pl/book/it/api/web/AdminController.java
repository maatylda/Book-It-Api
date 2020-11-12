package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.domain.Place;
import pl.book.it.api.domain.Room;
import pl.book.it.api.domain.Town;
import pl.book.it.api.model.Dto.PlaceDto;
import pl.book.it.api.model.Dto.RoomDto;
import pl.book.it.api.model.Dto.TownDto;
import pl.book.it.api.services.TownService;
import pl.book.it.api.services.place.PlaceService;
import pl.book.it.api.services.room.RoomService;
import pl.book.it.api.services.validation.BookingValidator;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping(WebConstants.API_ADMIN_PATH)
public class AdminController {

    private final PlaceService placeService;
    private final TownService townService;
    private final RoomService roomService;
    private final BookingValidator bookingValidator;

    @PostMapping("/places")
    public ResponseEntity<Place> createPlace(@Valid @RequestBody PlaceDto placeDto) throws URISyntaxException {
        if (bookingValidator.placeExistByName(placeDto.getName())) {
            return ResponseEntity.badRequest().build();
        }
        final Place place = placeService.createPlaceFromForm(placeDto);
        return ResponseEntity.created(new URI(WebConstants.API_ADMIN_PATH + "/places" + place.getId()))
                .body(place);
    }

    @PostMapping("/towns")
    public ResponseEntity<Town> createTown(@Valid @RequestBody TownDto townDto) throws URISyntaxException {
        if (bookingValidator.townExist(townDto.getName())) {
            return ResponseEntity.badRequest().build();
        }
        final Town town = townService.createTown(townDto.getName());
        return ResponseEntity.created(new URI(WebConstants.API_ADMIN_PATH + "/towns" + town.getId()))
                .body(town);
    }

    //roomDto have inside placeId
    //put? or post?
    @PutMapping("/places/{id}")
    public ResponseEntity<Room> createRoomInPlace(@Valid @RequestBody RoomDto roomDto,
                                                  @PathVariable("id") Long placeId) {
        if (bookingValidator.placeExist(placeId)) {
            final Room room = roomService.createRoom(roomDto, placeId);
        }

        return ResponseEntity.badRequest().build();
    }


}
