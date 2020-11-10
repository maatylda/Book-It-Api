package pl.book.it.api.services.room;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Place;
import pl.book.it.api.domain.Room;
import pl.book.it.api.model.forms.RoomForm;
import pl.book.it.api.repositories.PlaceRepository;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Transactional
public class RoomMapper {

    private final PlaceRepository placeRepository;

    public Room createFromForm (RoomForm roomForm){
        return Room.builder().place(getPlace(roomForm))
                .roomType(roomForm.getRoomType())
                .standard(roomForm.getStandard())
                .price(roomForm.getPrice())
                .bookings(new ArrayList<>())
                .build();
    }


    private Place getPlace(RoomForm roomForm) {
        return placeRepository.getOne(roomForm.getPlaceId());
    }
}
