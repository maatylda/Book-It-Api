package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.book.it.api.domain.Place;
import pl.book.it.api.model.Places;
import pl.book.it.api.services.PlaceService;

import java.time.LocalDate;
import java.util.Optional;


@RestController
@RequestMapping("/bia/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public Places getAllPlaces() {
        return new Places(placeService.getAllPlaces());
    }

    @GetMapping(path = "/{townName}")
    public Places getPlacesByTown(@PathVariable String townName) {
        return new Places(placeService.getAllPlacesInTown(townName));
    }

    @GetMapping(path = "/search")
    public Places getPlacesByTownAvaliableInDates(@RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                  @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
                                                  @RequestParam(name = "town") String townName) {
        return new Places(placeService.getAllPlacesInTownAvaliableInDates(dateFrom, dateTo, townName));
    }

    @GetMapping(path = "/{id}")
    public Place getPlaceById (@PathVariable Long id){
      return  placeService.getPlaceById(id);
    }


}
