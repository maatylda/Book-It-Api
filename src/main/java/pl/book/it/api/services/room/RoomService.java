package pl.book.it.api.services.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Place;
import pl.book.it.api.domain.Room;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.mappers.RoomMapStructMapper;
import pl.book.it.api.model.Dto.RoomDto;
import pl.book.it.api.model.Rooms;
import pl.book.it.api.repositories.RoomRepository;
import pl.book.it.api.services.place.PlaceService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapStructMapper roomMapStructMapper;
    private final PlaceService placeService;

    public Rooms findAllRoomsInPlace(Long placeId) {
        return new Rooms(roomRepository.findAllByPlace_Id(placeId).stream()
                .map(roomMapStructMapper::toRoomDto).collect(Collectors.toList()));
    }

    //TODO fix it in RoomRepository!!!
    public Rooms findAllRoomsInPlaceAvailableInDates(LocalDate dateFrom, LocalDate dateTo, Long placeId) {
        return new Rooms(roomRepository.findRoomsInPlaceAvailableInDates(dateFrom, dateTo, placeId).stream()
                .map(roomMapStructMapper::toRoomDto).collect(Collectors.toList()));
    }

    public RoomDto createRoom(RoomDto roomDto) {
        final Room room = roomMapStructMapper.toRoom(roomDto);
        setEmptyBookingsList(room);
        final Room savedRoom = roomRepository.save(room);
        final Place place = placeService.findPlaceById(roomDto.getPlaceId());
        place.getRooms().add(savedRoom);
        placeService.savePlace(place);


        return roomMapStructMapper.toRoomDto(roomRepository.save(savedRoom));
    }

    private void setEmptyBookingsList(Room room) {
        room.setBookings(new ArrayList<>());
    }

    public Room findRoomById(Long roomId) {
        return findOptionalRoomById(roomId).orElseThrow(() ->
                new BookItException("Wrong room id","roomId"));
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
