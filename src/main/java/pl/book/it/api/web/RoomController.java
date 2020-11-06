package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.book.it.api.model.Rooms;
import pl.book.it.api.services.RoomService;

@RestController
@RequestMapping("bia/places")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/{placeId}/rooms")
    public Rooms getRoomsInPlace (@PathVariable Long placeId){
        return new Rooms(roomService.getAllRoomsInPlace(placeId));
    }




}
