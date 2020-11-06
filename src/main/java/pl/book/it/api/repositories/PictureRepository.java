package pl.book.it.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
