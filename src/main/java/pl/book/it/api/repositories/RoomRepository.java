package pl.book.it.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    public List<Room> findAllByPlace_Id (Long placeId);
}
