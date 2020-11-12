package pl.book.it.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Town;
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

    public boolean isTownAlreadyExistInDatabase(String townName) {
        return townRepository.findByName(townName.toUpperCase()).isPresent();

    }

    public Optional<Town> getTownByName(String townName) {
        return townRepository.findByName(townName);
    }

    public Town createTown(String townName) {
        return townRepository.save(Town.builder().name(townName.toUpperCase()).build());
    }
}
