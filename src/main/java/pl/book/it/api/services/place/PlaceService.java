package pl.book.it.api.services.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Place;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.model.ApiErrors;
import pl.book.it.api.model.Dto.PlaceDto;
import pl.book.it.api.repositories.PlaceRepository;
import pl.book.it.api.services.validation.BookingValidator;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;
    private final BookingValidator bookingValidator;

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
                new BookItException(String.format("Place with given id %s does" +
                        " not exist", id), ApiErrors.PLACE_NOT_FOUND.getCode()));
    }

    public Place createPlace(PlaceDto placeDto) {
        if (bookingValidator.checkIfPlaceExistByName(placeDto.getName())) {
            //maybe redundant
            throw new BookItException(String.format("There is already place like %s in database", placeDto.getName()), ApiErrors.PLACE_NOT_FOUND.getCode());
        }
        final Place place = placeMapper.createPlace(placeDto);
        return placeRepository.save(place);
    }

    public void deletePlace(Long placeId) {
        final Place place = getPlaceById(placeId);
        placeRepository.delete(place);
    }

    public void updatePlace(Place place) {
        placeRepository.save(place);
    }
}

