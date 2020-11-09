package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.annotations.HandledByBookItExceptionHandler;
import pl.book.it.api.model.Rooms;
import pl.book.it.api.services.RoomService;

import java.time.LocalDate;

@HandledByBookItExceptionHandler
@RestController
@RequestMapping("/bia/places")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{placeId}/rooms")
    public Rooms getRoomsInPlace(@PathVariable Long placeId) {
        return new Rooms(roomService.getAllRoomsInPlace(placeId));
    }

    @GetMapping("/{placeId}/rooms/search")
    public Rooms getRoomsAvailableInDates(@RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                          @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
                                          @PathVariable Long placeId) {
        return new Rooms(roomService.getAllRoomsInPlaceAvailableInDates(dateFrom, dateTo, placeId));

    }


}
