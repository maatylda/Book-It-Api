package pl.book.it.api.model;


import org.springframework.data.jpa.domain.Specification;
import pl.book.it.api.domain.Place;
import pl.book.it.api.domain.Place_;


public class PlaceSpec {

    public static Specification<Place> isInTown2(String townName) {
        return (Specification<Place>) (root, criteriaQuery, criteriaBuilder) -> {
            root = criteriaQuery.from(Place.class);
            return criteriaBuilder.equal(root.get(Place_.town.getName()), townName);
        };
    }
}
