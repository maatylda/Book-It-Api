package pl.book.it.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
