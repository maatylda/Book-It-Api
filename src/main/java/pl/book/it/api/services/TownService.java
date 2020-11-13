package pl.book.it.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Town;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.model.ApiErrors;
import pl.book.it.api.repositories.TownRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TownService {

    private final TownRepository townRepository;

    public List<Town> getAllTowns() {
        return townRepository.findAll();
    }

    public Optional<Town> getTownByName(String townName) {
        return townRepository.findByName(townName.toUpperCase());
    }

    public Town createTown(String townName) {
        checkIfTheTownExist(townName);
        return townRepository.save(Town.builder().name(townName.toUpperCase()).build());
    }

    public void checkIfTheTownExist(final String townName) {
        getTownByName(townName).orElseThrow(() ->
                new BookItException(String.format("There is no such Town as %s in database", townName), ApiErrors.TOWN_NOT_FOUND.getCode()));
    }
}
