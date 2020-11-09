package pl.book.it.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.Town;

import java.util.Optional;

public interface TownRepository extends JpaRepository<Town, Long> {

    public Optional<Town> findByName(String townName);


}
