package pl.book.it.api.reposietories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.book.it.api.domain.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
