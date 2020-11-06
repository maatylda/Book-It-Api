package pl.book.it.api.reposietories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.Foto;

public interface FotoRepository extends JpaRepository<Foto, Long> {
}
