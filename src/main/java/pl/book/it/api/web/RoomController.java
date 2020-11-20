package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.annotations.HandledByBookItExceptionHandler;
import pl.book.it.api.model.Rooms;
import pl.book.it.api.services.room.RoomService;

import java.time.LocalDate;

@HandledByBookItExceptionHandler
@RestController
@RequestMapping(WebConstants.API_PLACES_PATH)
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{placeId}/rooms")
    public Rooms showRoomsInPlace(@PathVariable Long placeId) {
        return roomService.findAllRoomsInPlace(placeId);
    }

    //TODO fix it later in RoomRepository!!!
    @GetMapping("/{placeId}/rooms/search")
    public Rooms showRoomsAvailableInDates(@RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                           @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
                                           @PathVariable Long placeId) {
        return roomService.findAllRoomsInPlaceAvailableInDates(dateFrom, dateTo, placeId);
    }
}
