package pl.book.it.api.reposietories;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.Place;
import pl.book.it.api.domain.Town;

import java.util.Set;

public interface PlaceRepository extends JpaRepository<Place, Long> {

public Set<Place> findPlacesByTown (Town town);



}
