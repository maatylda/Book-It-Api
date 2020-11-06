package pl.book.it.api.reposietories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.book.it.api.domain.Place;

import java.time.LocalDate;
import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {


    public List<Place> findPlacesByTownName(String townName);


    @Query(value = "SELECT p FROM places p\n" +
            "LEFT JOIN fetch towns t   \n" +
            "LEFT JOIN fetch rooms r \n" +
            "LEFT JOIN fetch bookings b \n" +
            "WHERE t.name=:town_name AND NOT ((b.dateFrom BETWEEN :chosen_date_from AND :chosen_date_to) OR (b.dateTo BETWEEN :chosen_date_from AND :chosen_date_to) \n" +
            "OR(:chosen_date_from BETWEEN b.dateFrom AND b.dateTo ) OR (:chosen_date_to BETWEEN b.dateFrom AND b.dateTo ))")
    public List<Place> findPlaceInTownAvaliableInDates(@Param("chosen_date_from") LocalDate chosenDateFrom,
                                                       @Param("chosen_date_to") LocalDate chosenDateTo,
                                                       @Param("town_name") String town);
}
