package pl.book.it.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.book.it.api.domain.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT b FROM bookings b " +
            "WHERE b.user.email =:email")
    List<Booking> findAllBookingForUser(String email);

    //TODO check it again
    @Query(value = "SELECT b FROM bookings b " +
            "LEFT JOIN FETCH b.room r " +
            "WHERE r.id=:room_id AND " +
            "((b.dateFrom BETWEEN :chosen_date_from AND :chosen_date_to) OR " +
            "(b.dateTo BETWEEN :chosen_date_from AND :chosen_date_to) OR " +
            "(:chosen_date_from BETWEEN b.dateFrom AND b.dateTo) OR " +
            "(:chosen_date_to BETWEEN b.dateFrom AND b.dateTo))")
    List<Booking> findAllBookingsForRoomInDates(@Param("chosen_date_from") LocalDate chosenDateFrom,
                                                @Param("chosen_date_to") LocalDate chosenDateTo,
                                                @Param("room_id") Long roomId);
}
