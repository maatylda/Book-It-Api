package pl.book.it.api.services.town;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Town;
import pl.book.it.api.exceptions.BookItException;
import pl.book.it.api.mappers.TownMapper;
import pl.book.it.api.model.Dto.TownDto;
import pl.book.it.api.repositories.TownRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TownService {

    private final TownRepository townRepository;
    private final TownMapper townMapper;

    public List<TownDto> getAllTownsDto() {
        return getAllTowns().stream().map(townMapper::toTownDto).collect(Collectors.toList());
    }

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
                new BookItException(String.format("There is no such Town as %s in database", townName)));
    }
}
