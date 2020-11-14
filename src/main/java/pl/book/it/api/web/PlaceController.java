package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.annotations.HandledByBookItExceptionHandler;
import pl.book.it.api.domain.Place;
import pl.book.it.api.model.Places;
import pl.book.it.api.services.place.PlaceService;

import java.time.LocalDate;

@HandledByBookItExceptionHandler
@RestController
@RequestMapping(WebConstants.API_PLACES_PATH)
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public Places getAllPlaces() {
        return new Places(placeService.findAllPlaces());
    }

    @GetMapping(path = "/towns/{townName}")
    public Places getPlacesByTown(@PathVariable String townName) {
        return new Places(placeService.findAllPlacesInTown(townName));
    }

    @GetMapping(path = "/search")
    public Places getPlacesByTownAvailableInDates(@RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                  @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
                                                  @RequestParam(name = "town") String townName) {
        return new Places(placeService.findAllPlacesInTownAvailableInDates(dateFrom, dateTo, townName));
    }

    @GetMapping(path = "/{id}")
    public Place getPlaceById(@PathVariable Long id) {
        return placeService.findPlaceById(id);
    }


}
