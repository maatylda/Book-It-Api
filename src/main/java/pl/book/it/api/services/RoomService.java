package pl.book.it.api.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Room;
import pl.book.it.api.repositories.RoomRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> getAllRoomsInPlace (Long placeId){
      return roomRepository.findAllByPlace_Id(placeId);
    }

}
