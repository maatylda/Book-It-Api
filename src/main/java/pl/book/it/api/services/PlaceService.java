package pl.book.it.api.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.book.it.api.domain.Place;
import pl.book.it.api.reposietories.PlaceRepository;

import java.time.LocalDate;
import java.util.List;

import static pl.book.it.api.model.PlaceSpec.isInTown2;


@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public List<Place> getAllPlacesInTown(String townName) {
        return placeRepository.findPlacesByTownName(townName.toUpperCase());
    }

    public List<Place> getAllPlacesInTownAvaliableInDates(LocalDate dateFrom, LocalDate dateTo, String townName) {
        return placeRepository.findPlacesInTownAvaliableInDates(dateFrom, dateTo, townName.toUpperCase());
    }


    // trying to use specyfication not working
    public List<Place> getAllPlacesInTown2(String townName) {
        return placeRepository.findAll(isInTown2(townName.toUpperCase()));
    }

}

