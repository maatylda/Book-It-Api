package pl.book.it.api.services.room;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Place;
import pl.book.it.api.domain.Room;
import pl.book.it.api.model.Dto.RoomDto;
import pl.book.it.api.repositories.PlaceRepository;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Transactional
public class RoomMapper {

    private final PlaceRepository placeRepository;

    public Room createFromForm(RoomDto roomDto, Long placeId) {
        return Room.builder().place(getPlace(placeId))
                .roomType(roomDto.getRoomType())
                .standard(roomDto.getStandard())
                .price(roomDto.getPrice())
                .bookings(new ArrayList<>())
                .build();
    }


    private Place getPlace(Long placeId) {
        return placeRepository.getOne(placeId);
    }
}
