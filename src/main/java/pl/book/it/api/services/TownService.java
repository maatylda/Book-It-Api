package pl.book.it.api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.book.it.api.domain.Town;
import pl.book.it.api.repositories.TownRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TownService {

    private final TownRepository townRepository;

    public List<Town> getAllTowns() {
        return townRepository.findAll();
    }
}
