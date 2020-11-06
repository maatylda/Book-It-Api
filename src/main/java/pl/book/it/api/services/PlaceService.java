package pl.book.it.api.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.book.it.api.domain.Place;
import pl.book.it.api.reposietories.PlaceRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<Place> getAllPlaces() {
        Iterable<Place> all = placeRepository.findAll();
        ArrayList<Place> places = new ArrayList<>();
        all.forEach(places::add);
        return places;
    }

    public List<Place> getAllPlacesInTown(String townName) {
        return placeRepository.findPlacesByTownName(townName.toUpperCase());
    }

//    public List <Place> getAllPlacesAvaliableInDates (LocalDate dateFrom, LocalDate DateTo){
//        return placeRepository.findPlacesAvaliableInDates;
//    }

}

