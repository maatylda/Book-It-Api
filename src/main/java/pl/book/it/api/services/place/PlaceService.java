package pl.book.it.api.services.place;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Place;
import pl.book.it.api.domain.Town;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.mappers.PlaceMapStructMapper;
import pl.book.it.api.model.Dto.PlaceDto;
import pl.book.it.api.model.Places;
import pl.book.it.api.repositories.PlaceRepository;
import pl.book.it.api.services.town.TownService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapStructMapper placeMapStructMapper;
    private final TownService townService;

    public Places findAllPlaces() {
        return mapPlaceListToPlaces(placeRepository.findAll());
    }

    public Places findAllPlacesInTown(String townName) {
        return mapPlaceListToPlaces(placeRepository.findPlacesByTownName(townName.toUpperCase()));
    }

    public Places findAllPlacesInTownAvailableInDates(LocalDate dateFrom, LocalDate dateTo, String townName) {
        return mapPlaceListToPlaces(placeRepository.findPlacesInTownAvailableInDates(dateFrom, dateTo, townName.toUpperCase()));
    }
    private Places mapPlaceListToPlaces (List<Place> placeList) {
        return new Places(placeList.stream()
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
                new BookItException("No place with given id", "placeId"));
    }

    public PlaceDto findPlaceDtoByPlaceId(Long id) {
        return placeMapStructMapper.toPlaceDto(findPlaceById(id));
    }

    public PlaceDto createPlace(PlaceDto placeDto) {
        final Town town = townService.getTownByNameOrElseThrow(placeDto.getTownName());
        final Place place = placeMapStructMapper.toPlace(placeDto);
        placeSetUp(place);
        place.setTown(town);
        town.getPlaces().add(place);
        final Place savedPlace = placeRepository.save(place);
        townService.saveTown(town);
        return placeMapStructMapper.toPlaceDto(savedPlace);
    }

    private void placeSetUp(Place place) {
        setEmptyRoomsList(place);
        setEmptyPicturesList(place);
    }

    private void setEmptyPicturesList(Place place) {
        place.setPictures(new ArrayList<>());
    }

    private void setEmptyRoomsList(Place place) {
        place.setRooms(new ArrayList<>());
    }

    public void deletePlace(Long placeId) {
        final Place place = findPlaceById(placeId);
        placeRepository.delete(place);
    }

    public void savePlace(Place place) {
        placeRepository.save(place);
    }
}

