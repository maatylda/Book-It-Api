package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.annotations.HandledByBookItExceptionHandler;
import pl.book.it.api.domain.Place;
import pl.book.it.api.domain.Town;
import pl.book.it.api.model.Dto.PlaceDto;
import pl.book.it.api.model.Dto.RoomDto;
import pl.book.it.api.model.Dto.TownDto;
import pl.book.it.api.services.place.PlaceService;
import pl.book.it.api.services.room.RoomService;
import pl.book.it.api.services.town.TownService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@HandledByBookItExceptionHandler
@RestController
@RequiredArgsConstructor
@RequestMapping(WebConstants.API_ADMIN_PATH)
public class AdminController {

    private final PlaceService placeService;
    private final TownService townService;
    private final RoomService roomService;

    //todo
    @PostMapping("/places")
    public ResponseEntity<PlaceDto> createPlace(@Valid @RequestBody PlaceDto placeDto) throws URISyntaxException {
        final PlaceDto createdPlace = placeService.createPlace(placeDto);
        return ResponseEntity.created(new URI(WebConstants.API_ADMIN_PATH + "/places" + createdPlace.getId()))
                .body(createdPlace);
    }

    @DeleteMapping("/places/{id}")
    public void deletePlace(@PathVariable Long id) {
        placeService.deletePlace(id);
    }

    @PostMapping("/towns")
    public ResponseEntity<Town> createTown(@Valid @RequestBody TownDto townDto) throws URISyntaxException {
        final Town town = townService.createTown(townDto.getName());
        return ResponseEntity.created(new URI(WebConstants.API_ADMIN_PATH + "/towns" + town.getId()))
                .body(town);
    }

    @PostMapping("/places/{placeId}/rooms")
    public ResponseEntity<RoomDto> createRoomInPlace(@Valid @RequestBody RoomDto roomDto, @PathVariable Long placeId) throws URISyntaxException {
        if (!placeId.equals(roomDto.getPlaceId())) {
            return ResponseEntity.badRequest().build();
        }
        final Place place = placeService.findPlaceById(roomDto.getPlaceId());
        RoomDto roomDto1 = roomService.createRoom(roomDto);
        placeService.savePlace(place);
        return ResponseEntity.created(new URI(WebConstants.API_ADMIN_PATH + "/places/" + placeId + "/rooms/" + roomDto1.getId())).body(roomDto1);
    }
}
