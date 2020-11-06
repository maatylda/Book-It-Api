package pl.book.it.api.reposietories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
