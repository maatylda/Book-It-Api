package pl.book.it.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.book.it.api.model.Towns;
import pl.book.it.api.services.TownService;

@RestController
@RequestMapping("/towns")
@RequiredArgsConstructor
public class TownController {

    private final TownService townService;

    @GetMapping
    public Towns getAllTowns (){
        return new Towns(townService.findAllTowns());
    }


}
