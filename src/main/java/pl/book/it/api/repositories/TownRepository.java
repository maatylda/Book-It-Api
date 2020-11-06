package pl.book.it.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.Town;

import java.util.List;

public interface TownRepository extends JpaRepository<Town, Long> {



}
