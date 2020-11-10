package pl.book.it.api.services.room;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Room;
import pl.book.it.api.model.forms.RoomForm;
import pl.book.it.api.repositories.RoomRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public List<Room> getAllRoomsInPlace(Long placeId) {
        return roomRepository.findAllByPlace_Id(placeId);
    }

    public List<Room> getAllRoomsInPlaceAvailableInDates(LocalDate dateFrom, LocalDate dateTo, Long placeId) {
        final List<Room> roomsInPlaceAvailableInDates = roomRepository.findRoomsInPlaceAvailableInDates(dateFrom, dateTo, placeId);
//        if (roomsInPlaceAvailableInDates.isEmpty()){
//            throw new NoRoomsAvailable("There are no rooms in chosen dates");
        //tutaj raczej nie powinnam rzucac wyjątku :)
//        }
        return roomsInPlaceAvailableInDates;
    }

    //should take placeId from form? or not?
    public Room createRoom(RoomForm roomForm, Long placeId) {
        return roomMapper.createFromForm(roomForm, placeId);
    }

}
