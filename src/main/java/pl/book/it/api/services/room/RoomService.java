package pl.book.it.api.services.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Room;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.mappers.RoomMapStructMapper;
import pl.book.it.api.mappers.RoomMapper;
import pl.book.it.api.model.Dto.RoomDto;
import pl.book.it.api.model.Rooms;
import pl.book.it.api.repositories.RoomRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final RoomMapStructMapper roomMapStructMapper;

    public Rooms findAllRoomsInPlace(Long placeId) {
        return new Rooms(roomRepository.findAllByPlace_Id(placeId).stream()
                .map(roomMapStructMapper::toRoomDto).collect(Collectors.toList()));
    }

    //TODO fix it in RoomRepository!!!
    public Rooms findAllRoomsInPlaceAvailableInDates(LocalDate dateFrom, LocalDate dateTo, Long placeId) {
        return new Rooms(roomRepository.findRoomsInPlaceAvailableInDates(dateFrom, dateTo, placeId).stream()
                .map(roomMapStructMapper::toRoomDto).collect(Collectors.toList()));
    }

    public Room createRoom(RoomDto roomDto) {
        final Room room = roomMapper.createRoom(roomDto, roomDto.getPlaceId());
        roomRepository.save(room);
        return room;
    }

    public Room findRoomById(Long roomId) {
        return findOptionalRoomById(roomId).orElseThrow(() ->
                new BookItException("Wrong room id. There is no room with given id"));
    }

    public boolean roomWithIdExist(Long id) {
        return findOptionalRoomById(id).isPresent();
    }

    public Optional<Room> findOptionalRoomById(Long roomId) {
        return roomRepository.findById(roomId);
    }

    public void updateRoom(Room room) {
        roomRepository.save(room);
    }
}
