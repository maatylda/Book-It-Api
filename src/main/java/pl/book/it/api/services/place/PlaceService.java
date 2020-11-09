package pl.book.it.api.services.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Place;
import pl.book.it.api.exceptions.NoPlaceWithGivenIdException;
import pl.book.it.api.repositories.PlaceRepository;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public List<Place> getAllPlacesInTown(String townName) {
        return placeRepository.findPlacesByTownName(townName.toUpperCase());
    }

    public List<Place> getAllPlacesInTownAvailableInDates(LocalDate dateFrom, LocalDate dateTo, String townName) {
        return placeRepository.findPlacesInTownAvailableInDates(dateFrom, dateTo, townName.toUpperCase());
    }


    public Place getPlaceById(Long id) {
        return placeRepository.findById(id).orElseThrow(() ->
                new NoPlaceWithGivenIdException(String.format("Place with given id: %d does not exist", id)));
    }

    public Place createPlace(Place place) {
        return placeRepository.save(place);
    }
}

