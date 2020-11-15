package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.book.it.api.annotations.HandledByBookItExceptionHandler;
import pl.book.it.api.model.Towns;
import pl.book.it.api.services.town.TownService;

@HandledByBookItExceptionHandler
@RestController
@RequestMapping(WebConstants.API_TOWNS_PATH)
@RequiredArgsConstructor
public class TownController {

    private final TownService townService;

    @GetMapping
    public Towns getAllTowns() {
        return new Towns(townService.getAllTownsDto());
    }
}
