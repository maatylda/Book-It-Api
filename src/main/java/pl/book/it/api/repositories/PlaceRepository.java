package pl.book.it.api.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.book.it.api.domain.Place;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long>, JpaSpecificationExecutor<Place> {


    public List<Place> findPlacesByTownName(String townName);


    @Query(value = "SELECT p " +
            "FROM places p " +
            "LEFT JOIN p.town t " +
            "LEFT JOIN p.rooms r " +
            "LEFT JOIN r.bookings b " +
            "WHERE " +
            "t.name=:town_name AND " +
            "NOT (" +
            "(b.dateFrom BETWEEN :chosen_date_from AND :chosen_date_to) OR " +
            "(b.dateTo BETWEEN :chosen_date_from AND :chosen_date_to) OR " +
            "(:chosen_date_from BETWEEN b.dateFrom AND b.dateTo) OR " +
            "(:chosen_date_to BETWEEN b.dateFrom AND b.dateTo)" +
            ")"
    )
    public List<Place> findPlacesInTownAvaliableInDates(@Param("chosen_date_from") LocalDate chosenDateFrom,
                                                        @Param("chosen_date_to") LocalDate chosenDateTo,
                                                        @Param("town_name") String town);


    public Optional<Place> findById (Long id);
}
