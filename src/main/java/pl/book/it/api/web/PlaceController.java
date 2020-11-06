package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.book.it.api.model.Places;
import pl.book.it.api.services.PlaceService;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public Places getAllPlaces() {
        return new Places(placeService.getAllPlaces());
    }

    @GetMapping(path = "/{townName}")
    public Places findPlacesByTown(@PathVariable String townName) {
        return new Places(placeService.getAllPlacesInTown(townName));
    }
}
