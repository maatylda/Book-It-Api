package pl.book.it.api.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.book.it.api.domain.Room;
import pl.book.it.api.model.Dto.RoomDto;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface RoomMapStructMapper {

    @Mapping(source = "place.id", target = "placeId")
    RoomDto toRoomDto(Room room);

    @InheritInverseConfiguration
    Room toRoom(RoomDto roomDto);
}
