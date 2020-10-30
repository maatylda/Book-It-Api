package pl.book.it.api.reposietories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.Room;

public interface RoomRepository extends JpaRepository <Room,Long> {
}
