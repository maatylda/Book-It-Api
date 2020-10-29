package pl.book.it.api.reposietories;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {


}
