package pl.book.it.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    public List<Booking> findAllByRoomId(Long RoomId);

    public List<Booking> findAllByPlace_Id(Long PlaceId);

}
