package pl.book.it.api.services.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Room;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.model.ApiErrors;
import pl.book.it.api.model.Dto.RoomDto;
import pl.book.it.api.repositories.RoomRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public List<Room> findAllRoomsInPlace(Long placeId) {
        return roomRepository.findAllByPlace_Id(placeId);
    }

    public List<Room> findAllRoomsInPlaceAvailableInDates(LocalDate dateFrom, LocalDate dateTo, Long placeId) {
        return roomRepository.findRoomsInPlaceAvailableInDates(dateFrom, dateTo, placeId);
    }

    public Room createRoom(RoomDto roomDto) {
        final Room room = roomMapper.createRoom(roomDto, roomDto.getPlaceId());
        roomRepository.save(room);
        return room;
    }

    public Room findRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() ->
                new BookItException("Wrong room id. There is no room with given id", ApiErrors.ROOM_NOT_FOUND.getCode()));
    }

}
