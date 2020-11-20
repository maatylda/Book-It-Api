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
            "LEFT JOIN r.place p " +
            "LEFT JOIN FETCH r.bookings b " +
            "WHERE " +
            "p.id=:place_id AND " +
            "(NOT ((b.dateFrom BETWEEN :chosen_date_from AND :chosen_date_to) OR " +
            "(b.dateTo BETWEEN :chosen_date_from AND :chosen_date_to) OR " +
            "(:chosen_date_from BETWEEN b.dateFrom AND b.dateTo) OR " +
            "(:chosen_date_to BETWEEN b.dateFrom AND b.dateTo)" +
            ") OR r NOT IN(SELECT r FROM b.room) )")
    List<Room> findRoomsInPlaceAvailableInDates(@Param("chosen_date_from") LocalDate chosenDateFrom,
                                                @Param("chosen_date_to") LocalDate chosenDateTo,
                                                @Param("place_id") Long placeId);

}
