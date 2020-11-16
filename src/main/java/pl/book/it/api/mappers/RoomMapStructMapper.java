package pl.book.it.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.book.it.api.domain.Room;
import pl.book.it.api.model.Dto.RoomDto;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface RoomMapStructMapper {

    @Mapping(source= "place.getId", target = "placeId")
    Room toRoom(RoomDto roomDto);

    RoomDto toRoomDto(Room room);
}
