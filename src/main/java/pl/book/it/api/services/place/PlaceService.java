package pl.book.it.api.services.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Place;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.mappers.PlaceMapStructMapper;
import pl.book.it.api.model.Dto.PlaceDto;
import pl.book.it.api.model.Places;
import pl.book.it.api.repositories.PlaceRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapStructMapper placeMapStructMapper;

    public Places findAllPlaces() {
        return new Places(placeRepository.findAll()
                .stream()
                .map(placeMapStructMapper::toPlaceDto)
                .collect(Collectors.toList()));
    }

    public Places findAllPlacesInTown(String townName) {
        return new Places(placeRepository.findPlacesByTownName(townName.toUpperCase())
                .stream()
                .map(placeMapStructMapper::toPlaceDto)
                .collect(Collectors.toList()));
    }

    public Places findAllPlacesInTownAvailableInDates(LocalDate dateFrom, LocalDate dateTo, String townName) {
        return new Places(placeRepository.findPlacesInTownAvailableInDates(dateFrom, dateTo, townName.toUpperCase())
                .stream()
                .map(placeMapStructMapper::toPlaceDto)
                .collect(Collectors.toList()));
    }

    public boolean placeWithIdExist(Long id) {
        return findOptionalPlaceNById(id).isPresent();
    }

    public Optional<Place> findOptionalPlaceNById(Long id) {
        return placeRepository.findById(id);
    }

    public Place findPlaceById(Long id) {
        return findOptionalPlaceNById(id).orElseThrow(() ->
                new BookItException(String.format("Place with given id %s does not exist", id)));
    }

    public PlaceDto findPlaceDtoByPlaceId(Long id) {
        return placeMapStructMapper.toPlaceDto(findPlaceById(id));
    }

    public Place createPlace(PlaceDto placeDto) {
        final Place place = placeMapStructMapper.toPlace(placeDto);
        return placeRepository.save(place);
    }

    public void deletePlace(Long placeId) {
        final Place place = findPlaceById(placeId);
        placeRepository.delete(place);
    }

    public void savePlace(Place place) {
        placeRepository.save(place);
    }
}

