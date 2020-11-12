package pl.book.it.api.services.place;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Place;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.model.ApiErrors;
import pl.book.it.api.model.Dto.PlaceDto;
import pl.book.it.api.repositories.PlaceRepository;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

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
                new BookItException(400, ApiErrors.PLACE_NOT_FOUND.getMessage(), ApiErrors.PLACE_NOT_FOUND.getCode()));
    }

    public Place createPlace(PlaceDto placeDto) {
        final Place place = placeMapper.createFromForm(placeDto);
        return placeRepository.save(place);
    }
}

