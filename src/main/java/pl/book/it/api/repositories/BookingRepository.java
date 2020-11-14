package pl.book.it.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.book.it.api.domain.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT b FROM bookings b " +
            "WHERE b.user.email =:email")
    List<Booking> findAllBookingForUser(String email);

}
