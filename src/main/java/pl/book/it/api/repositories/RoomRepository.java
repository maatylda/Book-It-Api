package pl.book.it.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.book.it.api.domain.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByPlace_Id(Long placeId);

    @Query(value = "SELECT r " +
            "FROM rooms r " +
            "WHERE " +
            "r.place.id=:place_id AND r.id " +
            "NOT IN (SELECT b.room.id FROM bookings b WHERE "+
            "((b.dateFrom BETWEEN :chosen_date_from AND :chosen_date_to) OR " +
            "(b.dateTo BETWEEN :chosen_date_from AND :chosen_date_to) OR " +
            "(:chosen_date_from BETWEEN b.dateFrom AND b.dateTo) OR " +
            "(:chosen_date_to BETWEEN b.dateFrom AND b.dateTo)" +
            ") ) ")
    List<Room> findRoomsInPlaceAvailableInDates(@Param("chosen_date_from") LocalDate chosenDateFrom,
                                                @Param("chosen_date_to") LocalDate chosenDateTo,
                                                @Param("place_id") Long placeId);

}
